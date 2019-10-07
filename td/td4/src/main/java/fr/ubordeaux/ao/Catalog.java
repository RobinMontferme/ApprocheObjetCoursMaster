package fr.ubordeaux.ao;

import java.util.Set;

public interface Catalog {
    public int size();
    public String name();
    public Set<Reference> getOwnReferences();
    public Set<Reference> getAllReferences();
    public Reference findReferenceById(String id);
    public void addReference(Reference reference);
    public void removeReference(Reference reference);
    public void addSubCatalog(Catalog subCatalog);
}