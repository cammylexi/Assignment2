public class JobClass2 extends Job implements Comparable<Job> {
    private int priorityTime;

    public JobClass2(int jobId, int processingTime, int priorityTime) {
        super(jobId, processingTime);
        this.priorityTime = priorityTime;
    }

    public int getPriorityTime() {
        return priorityTime;
    }

    @Override
    public int compareTo(Job other) {
        if (other instanceof JobClass2) {
            JobClass2 otherJob = (JobClass2) other;
            int priorityComparison = Integer.compare(this.priorityTime, otherJob.priorityTime);
            if (priorityComparison != 0) {
                return priorityComparison;
            }
        }
        return Integer.compare(this.processingTime, other.getProcessingTime());
    }
}