About This Example:
===================
	This examples demonstrates the usage of Peaberry which is an extension library for Google Guice.
	Peaberry does what SpringDM does for the Spring dependency injection framework. Among other things
	Peabery enables you to inject OSGi services using plain Guice annotations.

	This example shows how the Smooks service can be injected into a simple Pojo class using Peaberry. 
	Currently this example uses a bundle activator but this will change and be simplfied in the near 
	future when the peaberry activation feature is available in a maven repository.

    See:
        1. The OSGI Bundle Activator in src/main/java/example/activator.PeaberryActivator.java.
		2. The "Pojo" class in src/main/java/example/Pojo.java".
        3. The input message in input-message.xml.
        4. smooks-config.xml.

How to Run?
===========
    Requirements:
        1. JDK 1.5
        2. Maven 2.x (http://maven.apache.org/download.html)
		3. Smooks-all bundle installed into an OSGi container, see 

    Building with Maven:
        1. "mvn clean install"
        2. "mvn depencency:copy-dependencies"

    Building with Gradle:
        1. "gradle clean install"
        2. "gradle copyDeps"

    Running:
        1. "mvn clean install"
        2. install target/dependencies/guice-2.0.jar to your OSGI container
        3. install target/dependencies/peaberry-1.1.1.jar to your OSGI container
        4. install target/milyn-smooks-example-peaberry-1.3.jar to your OSGi container

