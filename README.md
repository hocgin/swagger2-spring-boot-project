```sbtshell
.
├── README.md
├── pom.xml // 项目信息
├── swagger2-spring-boot-autoconfigure // 启动器
├── swagger2-spring-boot-parent  // 依赖
└── swagger2-spring-boot-starter  // Starter
```

http://localhost:8080/swagger-ui.html
### Maven
```
    <!--...-->
    <dependencies>
        <dependency>
            <groupId>in.hocg.swagger2</groupId>
            <artifactId>swagger2-spring-boot-starter</artifactId>
            <version>0.1.1-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>public</id>
            <name>public</name>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
    </repositories>
```
