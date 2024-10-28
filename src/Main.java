import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            PrintWriter writer = new PrintWriter("Execution-output.txt");

            // Task 1: Shortest Processing Time
            MinPQ<Job> pq1 = new MinPQ<>(100);
            String filename1 = "C:\\Users\\cammc\\IdeaProjects\\Assignment 2\\src\\task1-input.txt";
            LoadInputTask1(pq1, filename1);
            ExecuteTasks(pq1, "Task 1\nExecution Order", writer);

            // Task 2: Priority-Based Processing
            MinPQ<JobClass2> pq2 = new MinPQ<>(100);
            String filename2 = "C:\\Users\\cammc\\Downloads\\task2-input.txt";
            LoadInputTask2(pq2, filename2);
            ExecuteTasks(pq2, "Task 2\nExecution Order", writer);

            // Task 3: Arrival Time and Processing Time
            String filename3 = "C:\\Users\\cammc\\Downloads\\task3-input.txt";
            ArrayList<JobClass3> jobsTask3 = LoadInputTask3(filename3);
            ExecuteTask3(jobsTask3, writer);

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to output file: " + e.getMessage());
        }
    }

    public static void LoadInputTask1(MinPQ<Job> pq, String filename) {
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNext()) {
                int jobId = scan.nextInt();
                int processingTime = scan.nextInt();
                pq.insert(new Job(jobId, processingTime));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }

    public static void LoadInputTask2(MinPQ<JobClass2> pq, String filename) {
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNext()) {
                int jobId = scan.nextInt();
                int processingTime = scan.nextInt();
                int priorityTime = scan.nextInt();
                pq.insert(new JobClass2(jobId, processingTime, priorityTime));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }

    public static ArrayList<JobClass3> LoadInputTask3(String filename) {
        ArrayList<JobClass3> jobs = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNext()) {
                int jobId = scan.nextInt();
                int processingTime = scan.nextInt();
                int arrivalTime = scan.nextInt();
                jobs.add(new JobClass3(jobId, processingTime, arrivalTime));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
        return jobs;
    }

    public static void ExecuteTasks(MinPQ<? extends Job> pq, String taskName, PrintWriter writer) {
        int jobCount = 0;
        int currentTime = 0;
        int totalTime = 0;

        writer.print(taskName + ": [");
        System.out.print(taskName + ": [");
        while (!pq.isEmpty()) {
            Job job = pq.delMin();
            currentTime += job.getProcessingTime();
            totalTime += currentTime;
            writer.print(job.getJobId());
            System.out.print(job.getJobId());
            if (!pq.isEmpty()){
                writer.print(", ");
                System.out.print(", ");
            }
            jobCount++;
        }
        writer.println("]");
        System.out.println("]");


        // Calculate and print the average completion time
        double avgTime = (double) totalTime / jobCount;
        writer.println("Average completion time: " + avgTime);
        System.out.println("Average completion time: " + avgTime);

        System.out.println();
        writer.println();
    }

    public static void ExecuteTask3(ArrayList<JobClass3> jobs, PrintWriter writer) {
        MinPQ<JobClass3> pq = new MinPQ<>(100);
        int jobCount = 0;
        int currentTime = 0;
        int totalTime = 0;

        writer.print("Task 3\nExecution order: [");
        System.out.print("Task 3\nExecution order: [");

        while (!jobs.isEmpty() || !pq.isEmpty()) {
            ArrayList<JobClass3> incomingJobs = new ArrayList<>();
            for (JobClass3 job : jobs) {
                if (job.getArrivalTime() <= currentTime) {
                    pq.insert(job);
                    incomingJobs.add(job);
                }
            }
            jobs.removeAll(incomingJobs);

            if (!pq.isEmpty()) {
                JobClass3 currentJob = pq.delMin();
                currentTime += currentJob.getProcessingTime();
                totalTime += currentTime;
                writer.print(currentJob.getJobId());
                System.out.print(currentJob.jobId);
                if (!pq.isEmpty() || !jobs.isEmpty()) {
                    writer.print(", ");
                    System.out.print(", ");
                }
                jobCount++;
            } else if (!jobs.isEmpty()) {
                currentTime = jobs.get(0).getArrivalTime();
            }
        }

        writer.println("]");
        System.out.println("]");

        // Calculate and print the average completion time
        double avgTime = (double) totalTime / jobCount;
        writer.println("Average completion time: " + avgTime);
        System.out.println("Average completion time: " + avgTime);
    }
    public static double AverageTime(int totalTime, int jobCount){
        return(double) totalTime / jobCount;

    }

}






