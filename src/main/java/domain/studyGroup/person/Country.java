package domain.studyGroup.person;

public enum Country {
    UNITED_KINGDOM("unitedKingdom"),
    GERMANY("germany"),
    VATICAN("vatican"),
    SOUTH_KOREA("southKorea"),
    JAPAN("japan");

    private String name;
    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Country getCountry(String name){
        if (name.equals("unitedKingdom")){
            return UNITED_KINGDOM;
        }

        if (name.equals("germany")){
            return GERMANY;
        }

        if (name.equals("vatican")){
            return VATICAN;
        }

        if (name.equals("southKorea")){
            return SOUTH_KOREA;
        }

        if (name.equals("japan")){
            return JAPAN;
        }

        return null;
    }
}
