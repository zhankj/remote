package cn.cttic.sysframe.frame.domain;

public class SmTown {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SM_TOWN.TOWN_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    private String townId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SM_TOWN.TOWN_NAME
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    private String townName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SM_TOWN.COUNTY_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    private String countyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SM_TOWN.TOWN_ID
     *
     * @return the value of SM_TOWN.TOWN_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public String getTownId() {
        return townId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SM_TOWN.TOWN_ID
     *
     * @param townId the value for SM_TOWN.TOWN_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SM_TOWN.TOWN_NAME
     *
     * @return the value of SM_TOWN.TOWN_NAME
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public String getTownName() {
        return townName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SM_TOWN.TOWN_NAME
     *
     * @param townName the value for SM_TOWN.TOWN_NAME
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public void setTownName(String townName) {
        this.townName = townName == null ? null : townName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SM_TOWN.COUNTY_ID
     *
     * @return the value of SM_TOWN.COUNTY_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public String getCountyId() {
        return countyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SM_TOWN.COUNTY_ID
     *
     * @param countyId the value for SM_TOWN.COUNTY_ID
     *
     * @mbggenerated Thu Apr 10 11:00:29 CST 2014
     */
    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }
}