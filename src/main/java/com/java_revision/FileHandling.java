package com.java_revision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

class MyResource implements AutoCloseable {
    public void doSomething() {
        System.out.println("Doing something...");
    }

    @Override
    public void close() {
        System.out.println("Resource closed.");
    }
}

class SerializeStudent implements Serializable {

    class Address implements Serializable {
        String city;
        int zipcode;

        public Address(String city, int zipcode) {
            this.city = city;
            this.zipcode = zipcode;
        }

        @Override
        public String toString() {
            return "Address [city=" + city + ", zipcode=" + zipcode + "]";
        }

    }

    private static final long serialVersionUID = 1L;
    int id;
    String name;
    Address address;

    SerializeStudent(int id, String name, String city, int zipcode) {
        this.id = id;
        this.name = name;
        this.address = new Address(city, zipcode);
    }

    @Override
    public String toString() {
        return "SerializeStudent [id=" + id + ", name=" + name + ", address=" + address + "]";
    }

}

public class FileHandling {

    public void writeToFile(String filePath, List<String> lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));) {
            for (String string : lines) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFileDetectEncoding(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int b;
            while ((b = fis.read()) != -1) {
                if (b < 0x80)
                    continue; // ASCII byte

                // Check for UTF-8 multibyte sequence
                int bytesNeeded = 0;
                if ((b & 0xE0) == 0xC0)
                    bytesNeeded = 1; // 2-byte sequence
                else if ((b & 0xF0) == 0xE0)
                    bytesNeeded = 2; // 3-byte sequence
                else if ((b & 0xF8) == 0xF0)
                    bytesNeeded = 3; // 4-byte sequence
                else
                    return "Unknown"; // Invalid UTF-8 start byte

                for (int i = 0; i < bytesNeeded; i++) {
                    int nextByte = fis.read();
                    if (nextByte == -1 || (nextByte & 0xC0) != 0x80)
                        return "Unknown"; // Not a valid continuation byte
                }

                return "UTF-8"; // Valid multibyte UTF-8 detected
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }

        // If we made it here, all bytes were ASCII
        return "ASCII";
    }

    public List<String> readFile(String filePath) {
        List<String> output = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;

    }

    public void copyFileBinary(String sourcePath, String destPath) {
        try (FileInputStream fileInputStream = new FileInputStream(sourcePath);
                FileOutputStream fileOutputStream = new FileOutputStream(destPath)) {
            byte[] byteData = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(byteData)) != -1) {
                fileOutputStream.write(byteData, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        // Problem 1
        List<String> input = Arrays.asList("Test", "SDET", "Java");
        String filePath = "File1.txt";
        FileHandling fileHandling = new FileHandling();
        fileHandling.writeToFile(filePath, input);

        // Problem 2
        List<String> output = fileHandling.readFile(filePath);
        System.out.println("Output: "+ output);

        // Problem 3
        fileHandling.copyFileBinary("generic-badge.svg", "test.svg");
        try (MyResource res = new MyResource()) {
            res.doSomething();
        }

        // Problem 4
        SerializeStudent serializeStudent = new SerializeStudent(123, "ACE", "LA", 543211);
        // Serialize
        try (FileOutputStream fileOutputStream = new FileOutputStream("FileObjectStream.ser");) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(serializeStudent);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DeSeriaalize
        try (FileInputStream fInputStream = new FileInputStream("FileObjectStream.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fInputStream);
            serializeStudent = (SerializeStudent) ois.readObject();
            ois.close();
            System.out.println(serializeStudent.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
