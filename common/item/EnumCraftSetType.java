package riseautomatons.common.item;

public enum EnumCraftSetType {
	LENS("lens", "Lens"),
	EYEPIECE("eyepiece", "Eye Piece"),
	STONECOG("stonecog", "Stone Cog"),
	LOOP("loop", "Metal Ring"),
	CHAIN("chain", "Chain"),
	SPROCKET("sprocket", "Sprocket"),
	JOINT("joint","Joint"),
	SHARP("sharp", "Sharpened Point"),
	SMALLPLATE("smallplate", "Small Plate"),
	CYLINDER("cylinder", "Cylinder"),
	ROD("rod", "Rod"),
	PISTON("piston", "Tiny Piston"),
	ACTUATOR("actuator", "Soul Actuator"),
	CANVAS("canvas", "Canvas"),
	WING("wing", "Wing"),
	PASSIVE("passive", "Controller (Passive)"),
	AGGRESSIVE("aggressive", "Controller (Aggressive)"),
	JAW("jaw", "Jaw"),
	SENSOR("sensor", "NVS"),
	DRILL("drill", "Pickaxe Module"),
	ROLLERCHAIN("rollerchain", "Roller Chain"),
	SMALLHEAD("shead", "Small Head"),
	SMALLBODY("smallbody", "Small Body"),
	TOTEHEAD("totehead", "Tote Head"),
	MEDBODY("medbody", "Greater Body"),
	SMALLLEG("smallleg", "Small Leg"),
	SENTRYHEAD("sentryhead", "Sentry Head"),
	SALT("salt", "Salt"),
	SURf("sulf", "Sulf");

	public final String name;
	public final String fullname;

	EnumCraftSetType(String par1, String par2) {
		this.name = par1;
		this.fullname = par2;
	}
}