package sb.quantocusta.auth;

public enum Authority {

	// Naming conventions help navigation and avoid duplication
	// An Authority is named as VERB_SUBJECT_ENTITY
	// Verbs should initially follow CRUD (CREATE, RETRIEVE, UPDATE, DELETE)
	// Subjects are based on outward looking relationships (OWN, OTHERS)
	// Entities are based on primary entities (USER, CUSTOMER, CART, ITEM, INVOICE)

	// Roles (act as EnumSets from the fine grained authorities defined later)
	// Internal roles
	/**
	 * The administrator role that can reach administration API functions
	 */
	ROLE_ADMIN,
	/**
	 * Reserved for client applications when making upstream server calls
	 */
	ROLE_CLIENT,

	// User administration
	CREATE_USERS,
	RETRIEVE_USERS,
	UPDATE_USERS,
	DELETE_USERS,

	CHANGE_OWN_PASSWORD,
	// And so on
}