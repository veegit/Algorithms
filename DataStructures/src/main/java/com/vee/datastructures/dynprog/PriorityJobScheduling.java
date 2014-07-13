package com.vee.datastructures.dynprog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PriorityJobScheduling {

	public enum Algorithm {
		BY_DIFF, BY_RATIO;
	}

	List<Job> jobs;
	Algorithm algo = Algorithm.BY_DIFF;

	private interface Prioritizer {
		double prioritize();
	}

	private static abstract class Job implements Prioritizer {
		private final int id;
		protected final int weight;
		protected final int length;

		public Job(int id, int weight, int length) {
			this.id = id;
			this.weight = weight;
			this.length = length;
		}

		@Override
		public boolean equals(Object o) {
			return ((Job) o).id == id;
		}
	}

	private static class JobByDiff extends Job {

		public JobByDiff(int id, int weight, int length) {
			super(id, weight, length);
		}

		public double prioritize() {
			return this.weight - this.length;
		}
	}

	private static class JobByRatio extends Job {

		public JobByRatio(int id, int weight, int length) {
			super(id, weight, length);
		}

		public double prioritize() {
			return (double) this.weight / this.length;
		}
	}

	public PriorityJobScheduling(Algorithm algo) {
		this.algo = algo;
	}

	void init(String pathName) {
		try {
			Scanner scanner = new Scanner(new File(pathName));
			scanner.nextLine();
			int i = 0;
			jobs = new ArrayList<PriorityJobScheduling.Job>();
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				String num[] = s.split("\\s");
				int weight = Integer.parseInt(num[0]);
				int length = Integer.parseInt(num[1]);
				if (algo == Algorithm.BY_DIFF) {
					jobs.add(new JobByDiff(i, weight, length));
				} else {
					jobs.add(new JobByRatio(i, weight, length));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long schedule() {
		Collections.sort(jobs, new Comparator<Job>() {
			public int compare(Job o1, Job o2) {
				int sign = Double.compare(o2.prioritize(), o1.prioritize());
				if (sign == 0) {
					return Integer.signum(o2.weight - o1.weight);
				} else {
					return sign;
				}
			}
		});
		int completionTime = 0;
		long score = 0;
		for (Job job: jobs) {
			completionTime += job.length;
			//System.out.println(job.id + " " + completionTime);
			score += job.weight * completionTime;
		}
		return score;
	}

	public static PriorityJobScheduling build(String pathName, Algorithm algo) {
		PriorityJobScheduling sch = new PriorityJobScheduling(algo);
		sch.init(pathName);
		return sch;
	}

	public static void main(String[] args) {
		for (Algorithm algo : Algorithm.values()) {
			long sum = PriorityJobScheduling
					.build("/home/narayanan/workspace/data/coursera/jobs-scheduling.txt", algo)
					.schedule();
			System.out.println(sum);
		}
	}
}
