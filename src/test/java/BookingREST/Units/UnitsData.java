package BookingREST.Units;

public class UnitsData {
    public Units addUnits(String name, String abbr) {
        Units addUnit = new Units();
        addUnit.setName(name);
        addUnit.setAbbr(abbr);
        return addUnit;
    }
    public Units updateUnits(String name2, String abbr2) {
        Units updateUnit = new Units();
        updateUnit.setName(name2);
        updateUnit.setAbbr(abbr2);
        return updateUnit;
    }
}
