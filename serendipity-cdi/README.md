# Serendipity CDI

## Serendipity Logging

Serendipity provides a logging interceptor to conveniently log the return value of your methods.  

### LogAsString

This implementation logs the result of your method as a String value, by calling the toString() method of your return type.  

```java
@LogResult(implementation = LogAsString.class, logLevel = LogLevel.DEBUG)
public Apple createApple(String colour) {
       Apple apple = new Apple(colour);
       return apple;
}
// Apple[colour = red]
```

### LogAsXml

LogAsXml will attempt to marshal your methods return type into XML before logging the output.  

```java
@LogResult(implementation = LogAsXml.class, logLevel = LogLevel.DEBUG)
public Apple createApple(String colour) {
       Apple apple = new Apple(colour);
       return apple;
}
/*  <Apple>
 *    <colour>red</colour>
 *  </Apple>
 */
```

### LogAsJson

LogAsJson will attempt to marshal your methods return type into JSON before logging the output.   

```java
@LogResult(implementation = LogAsJson.class, logLevel = LogLevel.DEBUG)
public Apple createApple(String colour) {
       Apple apple = new Apple(colour);
       return apple;
}
/*  {
 *    "colour": "red"
 *  }
 */
```

### Registering The Interceptor

To register the logging interceptor, simply add the following interceptor definition to your beans.xml.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">

    <interceptors>
        <class>uk.co.cbsoftware.serendipity.util.cdi.logging.interceptor.LogResultInterceptor</class>
    </interceptors>

</beans>
```
