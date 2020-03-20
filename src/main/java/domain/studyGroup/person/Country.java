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
        Country[] allCountries = Country.values();

        for (Country country : allCountries){
            if (name.equals(country.getName())){
                return country;
            }
        }

        return null;
    }
}
