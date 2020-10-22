class Dashboard {
    public void accept(Alert alert){
        String alertName = alert.getClass.getSimpleName();
        String alertMessage = alert.getMessage();
        Date alertTimestamp = alert.getTimestamp();
        String alertPriority = alert.getPriority();
    }
}