package academy.learnprogramming;

//import the scanner
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {

        //create new scanner
        Scanner s = new Scanner(System.in);

        //declare the directory & location
        Directory dr = new Directory();
        while (true) {
            // This will print the Menu Option in the console
            System.out.println("1: Input New User Into The PhoneBook.");
            System.out.println("2: Remove A Person From The PhoneBook.");
            System.out.println("3: Update The Phone Number Of A User --> Type The Persons First Name Only.");
            System.out.println("4: Display All Contact Information In The PhoneBook.");
            System.out.println("5: Search A Phone Number Based On Person First Name.");
            System.out.println("6: Sort The Entire Phonebook.");
            System.out.println("7: To Exit The Program.");

            // This will display the options and store users input
            System.out.print("Enter your option: ");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter The First Name: ");
                    String first_name = s.next();
                    System.out.print("Enter The Last Name: ");
                    String last_name = s.next();
                    System.out.print("Enter The Phone Number: ");
                    String phone_number = s.next();
                    System.out.print("Enter The Gender: ");
                    char gender = s.next().charAt(0);
                    System.out.print("Enter The Address: ");
                    String address = s.next();
                    System.out.print("Enter The Email: ");
                    String email = s.next();
                    Node node = new Node(first_name, last_name, phone_number, address, gender, email);
                    dr.insert(node);
                    break;
                case 2:
                    System.out.print("Enter The Phone Number: ");
                    String ph_no = s.next();
                    dr.remove(ph_no);
                    break;
                case 3:
                    System.out.print("Enter The First name: ");
                    String f_n = s.next();
                    System.out.print("Enter The Updated Phone Number: ");
                    String ph_number = s.next();
                    dr.update(f_n, ph_number);
                    break;
                case 4:
                    dr.display();
                    break;
                case 5:
                    System.out.print("Enter First Name To Start The Search: ");
                    String f_name = s.next();
                    dr.search(f_name);
                    break;
                case 6:
                    dr.sort();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

        }
    }
}

// The Directory to store all phone numbers
class Directory {
    Node head, tail;

    Directory() {
        head = null;
        tail = null;
    }

    // This method will inserts a node into the list if phone number is new
    public void insert(Node node) {
        Node temp = head;
        while (temp != null && !temp.phone_number.equals(node.phone_number))
            temp = temp.next;
        if (temp != null) {
            System.out.println("The Phone You Entered already exists");
            return;
        }
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        System.out.println("Good Job My Guy :D ");
    }

    // This method will remove nodes with user given phone number
    public void remove(String ph_no) {
        Node prev = null;
        Node node = head;
        while (node != null && !node.phone_number.equals(ph_no)) {
            prev = node;
            node = node.next;
        }
        if (node == null)
            System.out.println("LOL! The Phone Number was Not Found");
        else if (prev == null) {
            head = head.next;
            if (head == null)
                tail = null;
            System.out.println("YAY!! It was Deleted Successfully :D ");
        } else {
            prev.first_name = node.first_name;
            prev.last_name = node.last_name;
            prev.phone_number = node.phone_number;
            prev.gender = node.gender;
            prev.address = node.address;
            prev.email = node.email;
            prev.next = node.next;
            System.out.println("Deleted Successfully");
        }
    }

    // This method will updates the phone number of stored persons
    public void update(String first_name, String phone_number) {
        boolean found = false;
        Node node = head;
        while (node != null) {
            if (node.first_name.equals(first_name)) {
                node.phone_number = phone_number;
                found = true;
            }
            node = node.next;
        }
        if (!found)
            System.out.println("The Person Was Not Found With The Given First Name my Guy");
        else
            System.out.println("Contact Information was Updated Successfully! Good Job My Guy!");
    }

    // This method will display all data in directory
    public void display() {
        if (head == null) {
            System.out.println("The Directory is Empty My Guy ");
            return;
        }
        System.out.println("Current Contact Information Stored : ");
        Node node = head;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    //This method is used if user searches for a person with first name
    public void search(String first_name) {
        Node node = head;
        boolean found = false;
        while (node != null) {
            if (node.first_name.equals(first_name)) {
                System.out.println(node);
                found = true;
            }
            node = node.next;
        }
        if (!found)
            System.out.println("The Person Was Not Found With The Given First Name my Guy");
    }

    // This will sorts all of the data into a linked lists according to first name
    public void sort() {
        Node node1 = head;
        while (node1 != null) {
            Node node2 = node1.next;
            while (node2 != null) {
                if (node1.first_name.compareTo(node2.first_name) > 0) {
                    swap(node1, node2);
                }
                node2 = node2.next;
            }
            node1 = node1.next;
        }
        System.out.println("The Phonebook Was Successfully Sorted");
    }

    public void swap(Node n1, Node n2) {
        String temp;

        temp = n1.first_name;
        n1.first_name = n2.first_name;
        n2.first_name = temp;

        temp = n1.last_name;
        n1.last_name = n2.last_name;
        n2.last_name = temp;

        temp = n1.phone_number;
        n1.phone_number = n2.phone_number;
        n2.phone_number = temp;

        temp = n1.address;
        n1.address = n2.address;
        n2.address = temp;

        temp = n1.email;
        n1.email = n2.email;
        n2.email = temp;

        char t = n1.gender;
        n1.gender = n2.gender;
        n2.gender = t;
    }
}

// Details of the Person
class Node {
    String first_name, last_name, phone_number, address, email;
    char gender;
    Node next = null;

    Node(String first_name, String last_name, String phone_number, String address, char gender, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }

    public String toString() {
        return "\nFirst Name: " + this.first_name + "\nLast Name: " + this.last_name + "\nPhone Number: " + this.phone_number +
                "\nAddress: " + this.address + "\nGender: " + this.gender + "\nEmail: " + this.email + "\n";
    }
}

