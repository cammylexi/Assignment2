class Job implements Comparable<Job> {
    protected int jobId;
    protected int processingTime;

    public Job(int jobId, int processingTime) {
        this.jobId = jobId;
        this.processingTime = processingTime;
    }

    public int getJobId() {
        return jobId;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }
}