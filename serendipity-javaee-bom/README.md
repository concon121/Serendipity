# Serendipity Java EE BOM

A Maven Bill of Materials to quickly get you started with Java EE.

To use the BOM, simply import the dependency in your projects dependency management section.

```xml
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>uk.co.cbsoftware.serendipity</groupId>
				<artifactId>serendipity-javaee-bom</artifactId>
				<version>${serendipity-version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```
