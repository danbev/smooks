About This Example:
===================
    This examles demonstrates basic usage of Smooks in an OSGI environment. A bundle activator is used to drive the demo. This
    will read the 'smooks-config.xml' that is in this bundle and use 'input-message.xml', also packaged inside the bundle, and 
    output the filtered result to the console.

	The following features are demonstrated:
	* Using the SmooksOSGIFactory to create the Smooks instance.

	See smooks-config.xml for inline comments.

    See:
        1. The "MainActivator" class in src/main/java/example/activator/MainActivator.java.
        2. The input message in input-message.xml.
        3. smooks-config.xml.

How to Run?
===========
    Requirements:
        1. JDK 1.5
        2. Maven 3.x (http://maven.apache.org/download.html)
           or 
           Gradle 1.0.x (http://gradle.org/downloads.html)


    Building with Maven:
        1. "mvn clean install"

    Building with Gradle:
        1. "gradle clean install"

    Running:
        1. Install and start smooks-all bundle into your target OSGi container. 
        2. Install this examples bundle jar into you target container
        3. Start the example bundle.
