package com.yiting.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {

	private String initPath;
	private String end;
	private List<String> results;
	private Phaser phaser;

	public FileSearch(String initPath, String end, Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.results = new ArrayList<String>();
		this.phaser = phaser;
	}

	private void directoryProcess(File file) {
		File[] list = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
	}

	private void fileProcess(File file) {
		if (file.getName().endsWith(end)) {
			results.add(file.getAbsolutePath());
		}

	}

	private void filterResults() {
		List<String> newResults = new ArrayList<String>();
		long actualDate = new Date().getTime();
		for (int i = 0; i < results.size(); i++) {
			File f = new File(results.get(i));
			long fileModified = f.lastModified();
			if (actualDate - fileModified < TimeUnit.MILLISECONDS.convert(1,
					TimeUnit.DAYS)) {
				newResults.add(results.get(i));
			}
		}
		results = newResults;
	}

	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.printf("%s :phase %d :0 results \n", Thread
					.currentThread().getName(), phaser.getPhase());
			System.out.printf("%s :end %d :0 results \n", Thread
					.currentThread().getName(), phaser.getPhase());
			phaser.arriveAndDeregister(); // 解除当前线程，使得线程个数减少
			return false;
		} else {
			System.out.printf("%s :phase %d : %d results \n", Thread
					.currentThread().getName(), phaser.getPhase(), results
					.size());
			phaser.arriveAndAwaitAdvance(); // 等待第二个阶段
			return true;
		}
	}

	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			System.out.printf("%s :%s\n", Thread.currentThread().getName(),
					results.get(i));
		}
		phaser.arriveAndAwaitAdvance();
	}

	@Override
	public void run() {
		// 第一个阶段 一起开始运行
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + ":starting");
		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}
		if (!checkResults()) {
			return;
		}
		filterResults();

		if (!checkResults()) {
			return;
		}
		showInfo();
		phaser.arriveAndDeregister();
		System.out.println(Thread.currentThread().getName() + "work complete");
	}

}
