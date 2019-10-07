package fr.ubordeaux.ao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.regex.*;


public class CatalogImpl implements Catalog {
    private Map<String, Reference> references;
    private List<Catalog> subCatalogs;
    public final String name;
    
    public CatalogImpl(String name) throws IllegalArgumentException {
        this.references = new HashMap<String, Reference>();
        this.subCatalogs = new ArrayList<Catalog>();
        String pattern ="[a-zA-Z]{3,10}";
        
        if(!Pattern.matches(pattern,name)) {
            throw new IllegalArgumentException("Exepected string of alphanumeric characters of size 3 to 10,got "+name+" size:"+ name.length());
        }
        this.name = name;
    }

    public int size() {
        return references.size();
    }
    public String name() {
        return name;
    }

    public Set<Reference> getOwnReferences() {
        Set<Reference> result = new HashSet<Reference>();
        result.addAll(references.values());
        return result;
    }

    public Set<Reference> getAllReferences() {
        Set<Reference> result = getOwnReferences();

        Set<Reference> tmpResults = new HashSet<Reference>();
        for(Catalog c : subCatalogs)
        {   tmpResults = c.getAllReferences();
            result.addAll(tmpResults);
        }
        return result;
    }

    public Reference findReferenceById(String id) {
        if (!references.containsKey(id)) throw new ReferenceManagementException("cannot find Reference, id unknown !");
        return references.get(id);
    }

    public void addReference(Reference reference) {
        references.put(reference.getId(), reference);
    }

    public void removeReference(Reference reference) {
        references.remove(reference.getId());
    }

    public void addSubCatalog(Catalog subCatalog){
        boolean nameAvailable = true;
        int i = 0;
        Catalog c;
        while(i < subCatalogs.size() && nameAvailable)
        {
            c = subCatalogs.get(i);
            if(c.name().equals(subCatalog.name())) {
                nameAvailable = false;
                System.out.println("Catalog name: "+subCatalog.name()+" is unavailable");
            }
            else {
                i++;
            }
        }
        if(nameAvailable)
        {
            subCatalogs.add(subCatalog);
            System.out.println("Addded subcatalog: "+subCatalog.name()+" ,to :"+name);
        }
    }
}