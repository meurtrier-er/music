public enum SingerJDBCDaoFactory implements AbstractSingerDaoFactory {
    INSTANCE;
    @Override
    public SingerJDBCDao createSingerDao() {
        return new SingerJDBCDao();
    }
}
