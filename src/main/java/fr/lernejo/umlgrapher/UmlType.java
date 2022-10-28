/////// CODE SUJET P3 ///////  V

package fr.lernejo.umlgrapher;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

//import java.util.List;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Set;


/**
 * Use to manage class et super base on the classe to give
 */


public class UmlType {

    /////// CODE SUJET P3 -création de structure de données- ///////  v
        private final Set<Class> types = new TreeSet<>(Comparator
            .<Class, String>comparing(Class::getSimpleName)
            .thenComparing(Class::getPackageName));

        public UmlType(Class[] classes) {
            this.getAllClass(classes);
        }
    /////// CODE SUJET P3 -création de structure de données- ///////  ^

    private void getAllClass(Class[] classes) {
        for (Class c : classes) {
            recursionSearch(c);
        }
    }

    private void getAllChild(Class c) {

        Reflections reflections;

        try {
            reflections = new Reflections(new ConfigurationBuilder().forPackage("")
                .forPackage("", c.getClassLoader())
            );
        } catch (RuntimeException e) {
            System.out.println("Class loader not found");
            return;
        }

        Set<Class<?>> subTypes = reflections.get(
            Scanners.SubTypes.get(c).asClass(this.getClass().getClassLoader(), c.getClassLoader())
        );
        for (Class classe : subTypes) {
            getAllChild(classe);
            if (!types.contains(classe)) types.add(classe);
        }
    }


    /////// CODE SUJET P3 - superclass recup classe parent - ///////  V
    private void recursionSearch(Class c) {

        Class superClass = c.getSuperclass();

        if (superClass != null
            && !superClass.getSimpleName().equals("Object"))
            recursionSearch(superClass);

        /////// CODE SUJET P3 - interface recup interface implémentée - ///////  V

        for (Class inter : c.getInterfaces()) {
            recursionSearch(inter);
        }
        this.getAllChild(c);
        types.add(c);
    }

    public Set<Class> getListOfClass() {
        return this.types;
    }
}

/////// CODE SUJET P3 ///////  ^