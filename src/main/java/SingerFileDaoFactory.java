public enum SingerFileDaoFactory implements AbstractSingerDaoFactory{
    INSTANCE;

    @Override
    public SingerFileDao createSingerDao() {
        return new SingerFileDao();
    }
}
