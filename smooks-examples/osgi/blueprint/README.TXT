About This Example:
===================
    This examles demonstrates using Smooks in an OSGI environment in combination the blueprint specification.
    The example uses a POJO which gets injected with a Smooks instance that it uses for performing a filtering
    operaration.

	The following features are demonstrated:
	* Using the blueprint specification to configure a POJO
    * Notice how the class 'example.Pojo' is not aware about any OSGI packages.

	See smooks-config.xml for inline comments.

    See:
        1. The "pojo.xml" blueprint configuration file in src/main/resource/OSGI-INF/blueprint/pojo.xml.
        2. smooks-config.xml.

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
