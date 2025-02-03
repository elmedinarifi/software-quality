# Software Quality

<h1 align="center">
  <a href="https://www.nhlstenden.com/"><img src="src/assets/nhl.png" alt="NHL Logo" height="150"></a>
</h1>
This project, developed for NHL Stenden University by Yunus Karakoc and Elmedin Arifi, focuses on improving and optimizing existing Java code.  The project emphasizes software quality principles through rigorous testing, analysis, and code re-optimization.

## Project Overview

The primary goal of this project is to enhance the quality and performance of Java code. This involves:

* **Testing:** Implementing and executing tests to identify areas for improvement and ensure code correctness.  *(Expand on the specific types of testing you intend to do here as you develop the project)*
* **Analysis:** Performing static and dynamic analysis to identify potential issues, inefficiencies, and areas for optimization. *(Specify the analysis tools or methods you plan to use)*
* **Re-optimization:** Refactoring and optimizing the code based on the analysis results to improve performance, maintainability, and overall quality.

## Technologies Used

* Java
* Docker

## Development Team

* Elmedin Arifi
* Yunus Karakoc

## Class Diagram Concept
```mermaid

classDiagram
    class JabberPoint {
        +main(String[] args) void
    }

    class SlideViewerFrame {
        -final static int WIDTH = 1200
        -final static int HEIGHT = 800
        -SlideViewerComponent slideViewerComponent
        -Presentation presentation
        +SlideViewerFrame(String title, Presentation presentation)
        +update(Presentation presentation, Slide data) void
    }

    class SlideViewerComponent {
        -Slide slide
        -Font labelFont
        -Presentation presentation
        +SlideViewerComponent(Presentation presentation)
        +getPreferredSize() Dimension
        +paintComponent(Graphics g) void
        +update(Presentation presentation, Slide data) void
    }

    class Presentation {
        -String showTitle
        -ArrayList~Slide~ showList
        -int currentSlideNumber
        -SlideViewerComponent slideViewerComponent
        +Presentation()
        +getSize() int
        +getTitle() String
        +setTitle(String title) void
        +setShowView(SlideViewerComponent slideViewerComponent) void
        +getSlide(int number) Slide
        +getCurrentSlide() Slide
        +setSlideNumber(int number) void
        +getSlideNumber() int
        +prevSlide() void
        +nextSlide() void
        +clear() void
        +append(Slide slide) void
    }

    class Slide {
        -String title
        -ArrayList~SlideItem~ items
        +Slide()
        +append(SlideItem item) void
        +getTitle() String
        +setTitle(String newTitle) void
        +append(int level, String message) void
        +getSlideItems() ArrayList~SlideItem~
    }

    class SlideItem {
        <<abstract>>
        +getLevel() int
        +getText() String
        +draw(Graphics g, Rectangle area, float scale, Style style) void
    }

    class TextItem {
        -String text
        +TextItem(int level, String text)
        +getText() String
        +draw(Graphics g, Rectangle area, float scale, Style style) void
    }

    class BitmapItem {
        -String imageName
        -BufferedImage bufferedImage
        +BitmapItem(int level, String name)
        +getText() String
        +draw(Graphics g, Rectangle area, float scale, Style style) void
    }

    class Style {
        -static Style[] styles
        -String fontName
        -int fontSize
        -int leading
        -Color color
        +Style(int indent, String color, int points, int leading)
        +getFont(float scale) Font
        +getIndent() int
        +getLeading() int
        +getColor() Color
    }

    class MenuController {
        +MenuController(SlideViewerFrame frame)
    }

    class KeyController {
        +KeyController(SlideViewerFrame frame)
    }

    JabberPoint --> SlideViewerFrame : Creates
    SlideViewerFrame --> SlideViewerComponent : Contains
    SlideViewerFrame --> Presentation : Contains
    SlideViewerComponent --> Presentation : Observes
    Presentation --> Slide : Contains
    Slide --> SlideItem : Contains
    SlideItem <|-- TextItem : Inherits
    SlideItem <|-- BitmapItem : Inherits
    SlideViewerFrame --> MenuController : Uses
    SlideViewerFrame --> KeyController : Uses
    Style --> TextItem : Used by
    Style --> BitmapItem : Used by
```

## Getting Started (Using Docker)

This project is designed to be run exclusively within a Docker container.  Follow these steps to get started:

1. **Prerequisites:** Ensure you have Docker installed on your system.  You can download and install Docker from [https://www.docker.com/](https://www.docker.com/).

2. **Building the Docker Image:** Navigate to the project directory in your terminal and run the following command to build the Docker image:

   ```bash
   docker build -t software-quality-project .
