package com.deloitte.gears.auth.controller;

import javax.validation.constraints.NotNull;

public class Credential {
    @NotNull
    private String username;
    @NotNull
    private String password;

    private boolean unpError;
    private boolean lockError;
    private boolean fpw;
    private boolean disabledError;

    public Credential() {
        super();
        username = null;
        password = null;
    }

    public Credential(String username, String password) {
    	super();
    	this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public void setUnpError(boolean err) {
        this.unpError = err;
    }

    public boolean isUnpError() {
        return this.unpError;
    }

    public void setLockError(boolean err) {
        this.lockError = err;
    }

    public boolean isLockError() {
        return this.lockError;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
	if(username != null && username.length() > 3) {
	    builder.append("'Username': " + username);
	} else {
		builder.append("'Username': " + "N/A");
	}
	builder.append(", 'authentication error': " + unpError);
	builder.append(", 'account locked': " + lockError);

	return builder.toString();
    }

	public void setFpw(boolean fpw) {
		this.fpw = fpw;
	}

    public boolean isFpw() {
        return this.fpw;
    }

	public boolean isDisabledError() {
		return disabledError;
	}

	public void setDisabledError(boolean disabledError) {
		this.disabledError = disabledError;
	}
}
