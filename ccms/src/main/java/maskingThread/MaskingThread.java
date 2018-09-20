package maskingThread;

class MaskingThread extends Thread {
    private volatile boolean run;
    private char echochar = '*';


    public MaskingThread(String prompt) {
        System.out.print(prompt);
    }


    public void run() {

        int priority = Thread.currentThread().getPriority();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        try {
            run = true;
            while(run) {
                System.out.print("\010" + echochar);
                try {
                    Thread.currentThread().sleep(1);
                }catch (InterruptedException iex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } finally {
            Thread.currentThread().setPriority(priority);
        }
    }


    public void stopMasking() {
        this.run = false;
    }
}