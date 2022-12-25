package com.xml.project;

import com.xml.project.a1.TestA1;
import com.xml.project.p1.TestP1;
import com.xml.project.z1.TestZ1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProjectApplication.class, args);

        TestA1 testA1 = new TestA1();
        testA1.doTest();

        TestP1 testP1 = new TestP1();
        testP1.doTest();

        TestZ1 testZ1 = new TestZ1();
        testZ1.doTest();

        TestA1.writeToDB();
    }

}
