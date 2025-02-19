package web.util;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {

    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance(){
        return instance;
    }

    private String userName;
    private String password;
    private String dbUrl;
    private String driverName;
    private int poolSize;

    private BlockingQueue<Connection> availableConnectionsQueue;
    private BlockingQueue<Connection> usedConnectionsQueue;


    private ConnectionPool() {
        DBResourceManager properties = DBResourceManager.getInstance();

        this.driverName = properties.getValue(DBParameters.DRIVER);
        this.dbUrl = properties.getValue(DBParameters.DB_URL);
        this.userName = properties.getValue(DBParameters.DB_USERNAME);
        this.password = properties.getValue(DBParameters.DB_PASSWORD);
        this.poolSize = Integer.parseInt(properties.getValue(DBParameters.POOL_SIZE));
    }

    public void initConnectionPool() throws ConnectionPoolException {
        try {
            Class.forName(driverName);

            availableConnectionsQueue = new ArrayBlockingQueue<Connection>(poolSize);
            usedConnectionsQueue = new ArrayBlockingQueue<Connection>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection realConnection = DriverManager.getConnection(dbUrl, userName, password);
                PooledConnection pooledConnection = new PooledConnection(realConnection);
                availableConnectionsQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(
                    "Can't find database driver class", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        try {
            Connection connection = null;
            connection = availableConnectionsQueue.take();
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }

    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(usedConnectionsQueue);
            closeConnectionsQueue(availableConnectionsQueue);
        } catch (SQLException e) {

        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue)
            throws SQLException {
        Connection connection;

        while ((connection = queue.poll()) != null) {

            if (!connection.getAutoCommit()) {
                connection.commit();
            }

            ((PooledConnection) connection).reallyClose();
        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            if (con != null) { con.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) { rs.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) { st.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection con, Statement st) {
        try {
            if (con != null) { con.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) { st.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseConnection(Connection connection) {

        if (connection instanceof PooledConnection) {
            usedConnectionsQueue.remove(connection);
            availableConnectionsQueue.offer(connection);
        } else {
            System.err.println("❌ Attempted to return an invalid connection.");
        }

    }

    private class PooledConnection implements Connection{
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("❌ Attempting to close already closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            releaseConnection(this);
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback();
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }
}
