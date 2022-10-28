////// CODE SUJET P1 Fin //////  v
package fr.lernejo.umlgrapher;

public class UmlGraph {
    private final Class[] classes;

    public UmlGraph(Class[] classes) {
        this.classes = classes;
    }

    ////// CODE SUJET P2 Fin //////  v
        public String as(GraphType graphType) {
            String result = "";
            if (graphType == GraphType.Mermaid) {
                try {
                    InternalGraphRepresentation graph = new InternalGraphRepresentation(classes);
                    result = new MermaidFormatter().format(graph);
                } catch (RuntimeException e) {
                    System.out.println("Error : " + e.getClass() + " - " + e.getMessage());
                }
            }
            return result;
        }
    ////// CODE SUJET P2 Fin //////  ^
}
////// CODE SUJET P1 Fin //////  ^