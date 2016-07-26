package test.service.jprofile;

public class Test extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Test t = new Test();
		for (int i = 1; i < 1000000; i++) {
			new HelloWorld();
			t.sleep(100); // 休眠100毫秒
		}
	}
}

class HelloWorld {
	public HelloWorld() {
		System.out.println("Hello Jayzee!");
	}
}