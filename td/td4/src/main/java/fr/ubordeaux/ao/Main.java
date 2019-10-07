package fr.ubordeaux.ao;
import java.util.Set;
import fr.ubordeaux.ao.CatalogImpl;
import fr.ubordeaux.ao.Reference;

public class Main{
    public static void catalogNameToohortTest() {
        Catalog cat = new CatalogImpl("Fn");
    }

    public static void catalogNameTooLongTest() {
        Catalog catLong = new CatalogImpl("Fnac-Catalogue");      
    }

    public static void subcatalogNameUnvailableTest()
    {
        Catalog cat = new CatalogImpl("BigCat");
        Catalog cat2 = new CatalogImpl("Fnac");
        Catalog cat3 = new CatalogImpl("Fnac");
        Catalog cat4 = new CatalogImpl("Fnac");
        Catalog cat5 = new CatalogImpl("Fnac");
        Catalog cat6 = new CatalogImpl("Fnac");
        Catalog cat7 = new CatalogImpl("Fnoc");

        cat.addSubCatalog(cat2);
        cat.addSubCatalog(cat3);
        cat.addSubCatalog(cat4);
        cat.addSubCatalog(cat5);
        cat.addSubCatalog(cat6);
        cat.addSubCatalog(cat7);
    }

    public static  void getOwnRefsTest() {
        Catalog cat = new CatalogImpl("BigCat");
        Catalog subCat1 = new CatalogImpl("Fnac");
        Catalog subCat2 = new CatalogImpl("Cdisc");
        Price smallPrice = new Price(1);
        Price medPrice = new Price(10);
        Price bigPrice = new Price(100);
        Reference smallRef = new Reference("a","SmolPKG", "It's a small package", smallPrice);
        Reference medRef = new Reference("m","MedKG", "It's a medium package", medPrice);
        Reference bigRef= new Reference("b","BiggPKG", "It's a big package", bigPrice);
        cat.addReference(smallRef);
        subCat1.addReference(medRef);
        subCat2.addReference(bigRef);
        cat.addSubCatalog(subCat1);
        cat.addSubCatalog(subCat2);
        Set<Reference> result = cat.getOwnReferences();
        for(Reference r : result)
        {
            System.out.println(r);
        }
    }
    public static void getAllRefTest() {
        Catalog cat = new CatalogImpl("BigCat");
        Catalog subCat1 = new CatalogImpl("Fnac");
        Catalog subCat2 = new CatalogImpl("Cdisc");
        Catalog secretCat = new CatalogImpl("Secret");

        Price smallPrice = new Price(1);
        Price medPrice = new Price(10);
        Price bigPrice = new Price(100);
        Price secretPrice = new Price(42);

        Reference smallRef = new Reference("a","SmolPKG", "It's a small package", smallPrice);
        Reference medRef = new Reference("m","MedKG", "It's a medium package", medPrice);
        Reference bigRef= new Reference("b","BiggPKG", "It's a big package", bigPrice);
        Reference secretRef = new Reference("s","SecretPKG","Wooh a secret Package",secretPrice);

        cat.addReference(smallRef);
        subCat1.addReference(medRef);
        subCat2.addReference(bigRef);
        secretCat.addReference(secretRef);

        subCat1.addSubCatalog(secretCat);
        cat.addSubCatalog(subCat1);
        cat.addSubCatalog(subCat2);

        Set<Reference> result = cat.getAllReferences();
        for(Reference r : result)
        {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        // catalogNameToohortTest();
        // catalogNameTooLongTest();
        // subcatalogNameUnvailableTest();
        // getOwnRefsTest();
        getAllRefTest();
    }
}