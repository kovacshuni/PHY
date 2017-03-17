package com.hunorkovacs.phy;

import java.util.Vector;

/**
 * A pointlike body excerting force to any objects around. The force's intensity is inverse square.
 * @see GravityField
 * @see PointlikeBody
 */
public class GravitationalPoint extends PointlikeBody {

    private Double g;

    GravitationalPoint(double m, Dimension p, Dimension v, Double g, PHYModel phymodel, String name) {
        super(m, p, v, phymodel, name);
        this.g = g;
    }

    public Dimension act(final PointlikeBody b) {
        Double xDiff = Math.abs(b.getPosition().getX() - this.getPosition().getX());
        Double yDiff = Math.abs(b.getPosition().getY() - this.getPosition().getY());
        Double diff = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        Double diffSq = Math.pow(diff, 2);
        Double intensity = g / diffSq;
        return new Dimension(intensity * xDiff, intensity * yDiff);
    }

    protected com.hunorkovacs.phy.Dimension calculateActingForces() {
        int i, j;
        boolean identicalSets;
        Vector bodyinris = new Vector();
        Vector bodyinforce = new Vector();
        Dimension F = new Dimension();

        for (i = 0; i < phymodel.getnRIS(); i++)
            if (isInsideRIS(p, phymodel.getRISAt(i))) bodyinris.add(phymodel.getRISAt(i));
        outer:
        phymodel.getGravitationalPoints().stream().forEach(gp -> {
            identicalSets = true;
            for (j = 0; j < phymodel.getnRIS(); j++) {
                RectangularIsolatorSpace ris = phymodel.getRISAt(j);
                if ((isInsideRIS(gp.getPosition(), ris)) ^ (isInsideRIS(p, ris))) {
                    identicalSets = false;
                    continue outer;
                }
            }
            if ((identicalSets) && (!gp.equals(this))) bodyinforce.add(gp);
        });
        for (i = 0; i < bodyinforce.size(); i++)
            F.setXY(F.getX() + ((GravitationalPoint) bodyinforce.get(i)).act(F).getX(), F.getY() + ((GravityField) bodyinforce.get(i)).act(m).getY());
        return F;
    }
}
