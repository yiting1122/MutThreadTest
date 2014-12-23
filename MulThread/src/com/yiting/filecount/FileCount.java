package com.yiting.filecount;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FileCount {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		try {
			long ret=(new FileCount()).getSize("I:\\book");
			long end=System.currentTimeMillis();
			System.out.println("the dir's size is: "+ret);
			System.out.println("the time cost is :"+(end-start));
			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public long getSize(final String fileName) throws InterruptedException, ExecutionException, TimeoutException{
		if(fileName==null){
			throw new IllegalArgumentException("file name is null");
		}
		
		final ExecutorService service=Executors.newFixedThreadPool(100);
		try {
			return getDirSize(new File(fileName), service);
		} finally{
			service.shutdownNow();
		}
		
	}
	

	public long getDirSize(final File file, final ExecutorService service)
			throws InterruptedException, ExecutionException, TimeoutException {
		if (file.isFile()) {
			System.out.println(file.getName()+file.length());
			return file.length();
		}
		long total = 0;
		final File[] files = file.listFiles();

		if (files != null) {
			List<Future<Long>> rets = new ArrayList<>();
			for (final File f : files) {
				rets.add(service.submit(new Callable<Long>() {

					@Override
					public Long call() throws Exception {
						return getDirSize(f, service);
					}
				}));
			}

			for (Future<Long> future : rets) {
				total += future.get(100, TimeUnit.SECONDS);
			}

		}

		return total;
	}
}
