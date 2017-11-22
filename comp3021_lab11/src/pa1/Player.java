// TODO: Complete based on UML Diagram.
// TODO: units contains an ArrayList of all Units belonging to this Player.
// TODO: readyAllUnits(): Only Ready Unit if it is still Alive.
// TODO: hasReadyUnits(): Ignore Dead Units.
// TODO: hasUnitsRemaining(): Eg, has Units that are still Alive.
// TODO: equals(): Players with same Name are equal.

package pa1;

import java.util.ArrayList;

public class Player {
    private String name;

    private ArrayList<Unit> units;

    public Player (String name) {
        this.name = name;
        this.units = new ArrayList<Unit>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Unit> getUnitList() {
        return units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public Unit getUnitById(char id) {
        for(Unit u : units){
            if(u.getId() == id) return u;
        }
        return null;
    }

    public void readyAllUnits () {
        for(Unit u : units) {
            if(u.isAlive()) {
                u.isReady = true;
            }
        }
    }

    public boolean hasReadyUnits () {
        for(Unit u : units)
            if(u.isAlive() && u.isReady())
                return true;
        return false;
    }


    public boolean hasUnitsRemaining () {
        for(Unit u : units)
            if(u.isAlive())
                return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player)
            return this.getName().equals(((Player)obj).getName());
        return false;
    }
}
