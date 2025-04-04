# Software Quality - JabberPoint

<h1 align="center">
  <a href="https://www.nhlstenden.com/"><img src="nhl.png" alt="NHL Logo" height="150"></a>
</h1>

## Project Overview

This project is a refactored version of JabberPoint, a Java-based presentation tool, developed for NHL Stenden University. The main focus was on improving the codebase by implementing software design principles and establishing proper software quality practices.

## Technologies & Tools Used

### Development
- Java 21
- Maven for build automation
- IntelliJ IDEA as IDE

### Testing
- JUnit 5 for unit testing

### Quality Assurance
- JaCoCo for code coverage analysis
- Checkstyle for code style enforcement
- GitHub Actions for CI/CD
- XML for presentation storage

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8+

### Installation
1. Clone the repository:
```bash
git clone https://github.com/elmedinarifi/software-quality.git
```

2. Build the project:
```bash
mvn clean install
```

### Running the Application
You can run the application in two ways:

1. Using Maven:
```bash
mvn exec:java
```

2. Using the JAR file (download from Releases):
```bash
java -jar software-quality-1.0.0.jar
```

## Releases

You can find the latest release [here](https://github.com/elmedinarifi/software-quality/releases). Each release includes:
- Executable JAR file
- Release notes

To run the application:
```bash
java -jar software-quality-1.0.0.jar
```

You can create a new release by:
1. Going to the "Releases" section on GitHub
2. Clicking "Create a new release"
3. Creating a new tag (e.g., v1.0.1)
4. Adding a title and description
5. Publishing the release

The workflow will automatically attach the compiled JAR file to your release.

## Testing and Code Coverage

### Running Tests
To run tests:
```bash
mvn test
```

### Code Coverage
There are two ways to view code coverage:

1. **IntelliJ IDEA Coverage** (Local Development):
   - Right-click on the test folder
   - Select "Run 'All Tests' with Coverage"
   - Coverage results will be displayed in the IDE

2. **JaCoCo Reports** (CI/CD Pipeline):
   - Go to GitHub Actions
   - Select the latest workflow run
   - Download the "jacoco-report" artifact
   - Open `index.html` in a web browser

To generate a local JaCoCo report:
```bash
mvn test jacoco:report
```
The report will be available at: `target/site/jacoco/index.html`

## CI/CD Pipeline

Our GitHub Actions workflow includes:
1. Code quality checks (Checkstyle)
2. Building the project
3. Running tests
4. Generating and storing JaCoCo coverage reports
5. Creating releases with executable JARs

## Development Team

- Elmedin Arifi!
- Yunus Karakoc