package model;

import java.util.LinkedList;

/**
 * @author bryantylai/LaiSaiHoo
 * @since 1.0
 * @version 1.1
 * <p>This class creates a container which stores the svg tag and all svg elements</p>
 */
public class PASVGContainer
{
    private PASVGTag svgTag;
    private String fileName;
    private LinkedList<PASVGElement> svgContainer;

    /**
     * Creates a PASVGContainer with only svgTag such that from a newly created svg
     *
     * @param svgTag a PASVGTag created based on the svg tag of svg file
     */
    public PASVGContainer(PASVGTag svgTag, String fileName)
    {
        this.svgTag = svgTag;
        this.fileName = fileName;
        this.svgContainer = new LinkedList<>();
    }

    /**
     * Creates a PASVGContainer with a pre-created list of shapes such that from existing svg
     *
     * @param svgTag a PASVGTag created based on the svg tag of svg file
     * @param shapes list of shapes from svg file
     */
    public PASVGContainer(PASVGTag svgTag, String fileName, LinkedList<PASVGElement> shapes)
    {
        this.svgTag = svgTag;
        this.fileName = fileName;
        this.svgContainer = shapes;
    }

    /**
     * @return the svgTag
     */
    public PASVGTag getSvgTag()
    {
        return svgTag;
    }
    
    /**
     * 
     * @return fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @return the svgContainer
     */
    public LinkedList<PASVGElement> getSvgContainer()
    {
        return svgContainer;
    }
}