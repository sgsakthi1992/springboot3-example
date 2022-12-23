## SpringBoot 3

#### Changes:

* Jakarta 10
  * javax.persistence -> jakarta.persistence eg: Entity, Id
* GraalVM Native Image Support
  * use jdk 11 GraalVM
  * mvn -Pnative native:compile 
    * use Visual Studio 2019 -> x64 Native Tools Command Prompt for VS 2019
  *  to run the image ./target/<name_of_the_image>
* Spring Data 2022.0
  * New interface ListCrudRepository 
    * eg: it returns the List for findAll() whereas the CrudRepository interface returns Iterable
* Http Interfaces
  * use an Interface to exchange the data (JsonPlaceholderService.java), old way in PostService.java
  * Steps are in Springboot3ExampleApplication.java
    * create a WebClient from webflux dependency with the base url
    * create HttpServiceProxyFactory with the web client
    * use factory to create the implementation for the interface
* H2
  * no need to declare the datasource, it will be added by default
  * Use below properties to set default name for the database
    * spring.datasource.name: blog
    * spring.datasource.generate-unique-name: false
* Observability - ability to observe the internal state of a running system from outside. It consists of the three pillars logging, metrics and traces.
  * For metrics & traces, SpringBoot uses Micrometer observation
  * Zipkin: http://localhost:9411/zipkin/ (docker-compose.yml)
  * Customer Observation is in Springboot3ExampleApplication.java
* Problem details for HTTP APIs
  * refer ExceptionHandlerAdvice.java
* Spring Security
  * security config class no longer can extend WebSecurityConfigurerAdapter
  * In HttpSecurity, authorizeRequest is deprecated, use authorizeHttpRequests. Matchers like mvc matchers, regex matchers are removed, use requestMatchers.
  * refer SecurityConfig.java