public class JobClass3 extends Job implements Comparable<Job>{

    private int arrivalTime;

    public JobClass3(int jobId, int processingTime, int arrivalTime) {
        super(jobId, processingTime);
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public int compareTo(Job other) {
        if (other instanceof JobClass3) {
            JobClass3 otherJob = (JobClass3) other;
            int processingComparison = Integer.compare(this.processingTime, otherJob.processingTime);
            if (processingComparison != 0) {
                return processingComparison;
            }
            return Integer.compare(this.arrivalTime, otherJob.arrivalTime);
        }
        return Integer.compare(this.processingTime, other.getProcessingTime());
    }
}
