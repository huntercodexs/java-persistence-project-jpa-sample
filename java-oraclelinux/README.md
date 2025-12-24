# Java + Oracle Linux
Sample project

# Troubleshoot

<pre>
sqlplus sys/oracle1Ipw@ORCLPDB1 as sysdba
CREATE USER DEVEL IDENTIFIED BY oracle1Ipw;
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE TO DEVEL;
ALTER USER DEVEL QUOTA 50m ON SYSTEM;
CREATE SMALLFILE TABLESPACE DEVEL DATAFILE '/opt/oracle/oradata/ORCLCDB/ORCLPDB1/devel.dbf' SIZE 1G;
ALTER DATABASE DEFAULT TABLESPACE DEVEL;
ALTER USER DEVEL QUOTA UNLIMITED ON SYSTEM;
ALTER USER DEVEL QUOTA UNLIMITED ON DEVEL;
SELECT * FROM ALL_USERS au;
SELECT * FROM ALL_USERS au WHERE au.USERNAME = 'DEVEL';
EXIT;
</pre>

# Java 8


```properties
# SPRING JPA CONFIG
#-------------------------------------------------------------------------------------------------------------------
#spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
#spring.datasource.url=jdbc:oracle:thin:@//192.168.0.204:1521/ORCLPDB1
spring.datasource.url=jdbc:oracle:thin:@//oraclelinux:1521/ORCLPDB1
#Use SYSTEM id error (ORA-01031: privilegios insuficientes)
spring.datasource.username=DEVEL
spring.datasource.password=oracle1Ipw
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle10gDialect
```

```java
package com.huntercodexs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(generator = "SEQ_CUSTOMER", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_CUSTOMER", sequenceName = "SEQ_CUSTOMER", allocationSize = 1, initialValue = 1)
    private long id;

    @Column
    private int personType;

    @Column
    private String name;

    @Column
    private String identification;

    @Column
    private Date bornDate;

    @Column
    private Date purchaseDate;

    @Column
    private String contractNumber;

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.1.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<groupId>com.huntercodexs</groupId>
	<artifactId>JAVA-ORACLELINUX</artifactId>
	<version>22.01.1-SNAPSHOT</version>
	<name>JAVA-ORACLELINUX</name>
	<description>JAVA-ORACLELINUX</description>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
		<spring-cloud.version>Finchley.RC1</spring-cloud.version>
	</properties>

	<dependencies>

		<!--SPRING-BOOT-STARTER-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!--REST TEMPLATE-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.httpcomponents</groupId><!--HTTP PATCH METHOD-->
			<artifactId>httpclient</artifactId>
		</dependency>

		<!--DATABASE-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc8</artifactId>
			<version>19.3.0.0</version>
			<scope>runtime</scope>
		</dependency>

		<!--TOOLS/LOG-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>net.minidev</groupId>
			<artifactId>json-smart</artifactId>
			<version>2.3</version>
			<scope>compile</scope>
		</dependency>
					
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<fork>true</fork>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
				<executions>
	                <execution>
	                    <goals>
	                        <goal>repackage</goal>
	                        <goal>build-info</goal>
	                    </goals>
	                </execution>
	            </executions>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>

</project>
```

# Java 21

```properties
# SPRING JPA CONFIG
#-------------------------------------------------------------------------------------------------------------------
#spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.url=jdbc:oracle:thin:@//192.168.0.204:1521/ORCLPDB1
#spring.datasource.url=jdbc:oracle:thin:@//oraclelinux:1521/ORCLPDB1
#Use SYSTEM id error (ORA-01031: privilegios insuficientes)
spring.datasource.username=DEVEL
spring.datasource.password=oracle1Ipw
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

```java
package com.huntercodexs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(generator = "SEQ_CUSTOMER", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_CUSTOMER", sequenceName = "SEQ_CUSTOMER", allocationSize = 1, initialValue = 1)
    private long id;

    @Column
    private int personType;

    @Column
    private String name;

    @Column
    private String identification;

    @Column
    private Date bornDate;

    @Column
    private Date purchaseDate;

    @Column
    private String contractNumber;

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.13</version>
		<relativePath/>
	</parent>

	<version>1.0.0</version>
	<packaging>jar</packaging>
	<groupId>com.huntercodexs</groupId>

	<artifactId>java-oraclelinux</artifactId>
	<name>java-oraclelinux</name>
	<description>java-oraclelinux</description>

	<properties>
		<java.version>21</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>

	<dependencies>

		<!--SPRING-BOOT-STARTER-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<artifactId>spring-boot-starter-logging</artifactId>
					<groupId>org.springframework.boot</groupId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</dependency>

		<!--DATABASE-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
			<version>23.4.0.24.05</version>
			<scope>runtime</scope>
		</dependency>

		<!--TOOLS/LOG-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>net.minidev</groupId>
			<artifactId>json-smart</artifactId>
			<version>2.3</version>
			<scope>compile</scope>
		</dependency>

		<!--Tests: Mockito Inline para Java 21 -->
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-inline</artifactId>
			<version>5.2.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
					
	</dependencies>

	<build>
		<plugins>

			<!--maven-compiler-plugin-->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<parameters>true</parameters><!--Required for RateLimitServiceBus-->
				</configuration>
			</plugin>

			<!--maven-surefire-plugin-->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
			</plugin>

			<!--maven-dependency-plugin-->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-dependency-plugin</artifactId>
			</plugin>

			<!--srcclr-maven-plugin-->
			<plugin>
				<groupId>com.srcclr</groupId>
				<artifactId>srcclr-maven-plugin</artifactId>
			</plugin>

			<!--spring-boot-maven-plugin-->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>
```
