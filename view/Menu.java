package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;
    protected String[] selection;

    public Menu() {
    }

    public Menu(String title, String[] mc) {
        this.title = title;
        this.selection = mc;
        // mChon = new ArrayList<>();
        // for (String s : mc) {
        // mChon.add((T) s);
        // }
    }

    public void display() {
        System.out.println(title);
        System.out.println("---------------------");
        // for (int i = 0; i < mChon.size(); i++) {
        // System.out.println((i + 1) + "." + mChon.get(i));
        // }
        for (int i = 0; i < selection.length; i++) {
            System.out.println((i + 1) + "." + selection[i]);
        }
    }

    public int getSelected() {
        display();
        Scanner sc = new Scanner(System.in);
        System.out.print("Your choice is:.. ");
        return sc.nextInt();
    }

    public abstract void execute(int n);

    public void run() {
        while (true) {
            try {
                int n = getSelected();
                if (n > selection.length || n < 1) {
                    System.out.println("Your selection is invalid. Please enter again!!!");
                }
                execute(n);
            } catch (RuntimeException e) {
                System.out.println("You entered the wrong request!!! Please enter a number!!!");
            } catch (Exception e) {
                System.out.println("Please re-enter your choice as number!!!");
            }
        }
    }
}
