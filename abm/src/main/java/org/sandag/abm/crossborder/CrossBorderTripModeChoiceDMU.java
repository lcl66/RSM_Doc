package org.sandag.abm.crossborder;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.sandag.abm.ctramp.McLogsumsCalculator;

import com.pb.common.calculator.IndexValues;
import com.pb.common.calculator.VariableTable;

public class CrossBorderTripModeChoiceDMU
        implements Serializable, VariableTable
{
	
    protected transient Logger logger = Logger.getLogger(CrossBorderTripModeChoiceDMU.class);

    protected static final int                WTW = McLogsumsCalculator.WTW;
    protected static final int                WTD = McLogsumsCalculator.WTD;
    protected static final int                DTW = McLogsumsCalculator.DTW;
    protected static final int                NUM_ACC_EGR = McLogsumsCalculator.NUM_ACC_EGR;
    
    protected static final int                OUT = McLogsumsCalculator.OUT;
    protected static final int                IN = McLogsumsCalculator.IN;
    protected static final int                NUM_DIR = McLogsumsCalculator.NUM_DIR;

    protected int          tourDepartPeriod;
    protected int          tourArrivePeriod;
    protected int          tripPeriod;
    protected int          workTour;
    protected int          outboundStops;
    protected int          returnStops;
    protected int          firstTrip;
    protected int          lastTrip;
    protected int          tourModeIsDA;
    protected int          tourModeIsS2;
    protected int          tourModeIsS3;
    protected int          tourModeIsWalk;
    protected int          tourCrossingIsSentri;
    protected float        hourlyParkingCostTourDest;
    protected float        dailyParkingCostTourDest;
    protected float        monthlyParkingCostTourDest;
    protected int          tripOrigIsTourDest;
    protected int          tripDestIsTourDest;
    protected float        hourlyParkingCostTripOrig;
    protected float        hourlyParkingCostTripDest;
    protected float        workTimeFactor;
    protected float        nonWorkTimeFactor;
    protected int          avAvailable;

    protected double       nmWalkTime;
    protected double       nmBikeTime;
    protected HashMap<String, Integer> methodIndexMap;
    protected double ivtCoeff;
    protected double costCoeff;
    protected double walkTransitLogsum;
    protected double pnrTransitLogsum;
    protected double knrTransitLogsum;

    protected IndexValues              dmuIndex;
    protected int outboundHalfTourDirection;

    protected float waitTimeTaxi;
    protected float waitTimeSingleTNC;
    protected float waitTimeSharedTNC;

   public CrossBorderTripModeChoiceDMU(CrossBorderModelStructure modelStructure, Logger aLogger)
    {
        if (aLogger == null)
        {
            aLogger = Logger.getLogger("crossBorderModel");
        }
        logger = aLogger;
        setupMethodIndexMap();
        dmuIndex = new IndexValues();
  
    }

    /**
     * Set this index values for this tour mode choice DMU object.
     * 
     * @param hhIndex
     *            is the DMU household index
     * @param zoneIndex
     *            is the DMU zone index
     * @param origIndex
     *            is the DMU origin index
     * @param destIndex
     *            is the DMU desatination index
     */
    public void setDmuIndexValues(int hhIndex, int zoneIndex, int origIndex, int destIndex,
            boolean debug)
    {
        dmuIndex.setHHIndex(hhIndex);
        dmuIndex.setZoneIndex(zoneIndex);
        dmuIndex.setOriginZone(origIndex);
        dmuIndex.setDestZone(destIndex);

        dmuIndex.setDebug(false);
        dmuIndex.setDebugLabel("");
        if (debug)
        {
            dmuIndex.setDebug(true);
            dmuIndex.setDebugLabel("Debug MC UEC");
        }

    }

    public IndexValues getDmuIndexValues()
    {
        return dmuIndex;
    }

    /**
     * @return the tripPeriod
     */
    public int getTripPeriod()
    {
        return tripPeriod;
    }

    /**
     * @param tripPeriod
     *            the tripPeriod to set
     */
    public void setTripPeriod(int tripPeriod)
    {
        this.tripPeriod = tripPeriod;
    }

    /**
     * @return the workTour
     */
    public int getWorkTour()
    {
        return workTour;
    }

    /**
     * @param workTour
     *            the workTour to set
     */
    public void setWorkTour(int workTour)
    {
        this.workTour = workTour;
    }

    /**
     * @return the outboundStops
     */
    public int getOutboundStops()
    {
        return outboundStops;
    }

    /**
     * @param outboundStops
     *            the outboundStops to set
     */
    public void setOutboundStops(int outboundStops)
    {
        this.outboundStops = outboundStops;
    }

    /**
     * @return the returnStops
     */
    public int getReturnStops()
    {
        return returnStops;
    }

    /**
     * @param returnStops
     *            the returnStops to set
     */
    public void setReturnStops(int returnStops)
    {
        this.returnStops = returnStops;
    }

    /**
     * @return the firstTrip
     */
    public int getFirstTrip()
    {
        return firstTrip;
    }

    /**
     * @param firstTrip
     *            the firstTrip to set
     */
    public void setFirstTrip(int firstTrip)
    {
        this.firstTrip = firstTrip;
    }

    /**
     * @return the lastTrip
     */
    public int getLastTrip()
    {
        return lastTrip;
    }

    /**
     * @param lastTrip
     *            the lastTrip to set
     */
    public void setLastTrip(int lastTrip)
    {
        this.lastTrip = lastTrip;
    }

    /**
     * @return the tourModeIsDA
     */
    public int getTourModeIsDA()
    {
        return tourModeIsDA;
    }

    /**
     * @param tourModeIsDA
     *            the tourModeIsDA to set
     */
    public void setTourModeIsDA(int tourModeIsDA)
    {
        this.tourModeIsDA = tourModeIsDA;
    }

    /**
     * @return the tourModeIsS2
     */
    public int getTourModeIsS2()
    {
        return tourModeIsS2;
    }

    /**
     * @param tourModeIsS2
     *            the tourModeIsS2 to set
     */
    public void setTourModeIsS2(int tourModeIsS2)
    {
        this.tourModeIsS2 = tourModeIsS2;
    }

    /**
     * @return the tourModeIsS3
     */
    public int getTourModeIsS3()
    {
        return tourModeIsS3;
    }

    /**
     * @param tourModeIsS3
     *            the tourModeIsS3 to set
     */
    public void setTourModeIsS3(int tourModeIsS3)
    {
        this.tourModeIsS3 = tourModeIsS3;
    }

    /**
     * @return the tourModeIsWalk
     */
    public int getTourModeIsWalk()
    {
        return tourModeIsWalk;
    }

    /**
     * @param tourModeIsWalk
     *            the tourModeIsWalk to set
     */
    public void setTourModeIsWalk(int tourModeIsWalk)
    {
        this.tourModeIsWalk = tourModeIsWalk;
    }

    /**
     * @return the tourModeIsSentri
     */
    public int getTourCrossingIsSentri()
    {
        return tourCrossingIsSentri;
    }

    /**
     * @param tourModeIsSentri
     *            the tourModeIsSentri to set
     */
    public void setTourCrossingIsSentri(int tourCrossingIsSentri)
    {
        this.tourCrossingIsSentri = tourCrossingIsSentri;
    }

    /**
     * @return the hourlyParkingCostTourDest
     */
    public float getHourlyParkingCostTourDest()
    {
        return hourlyParkingCostTourDest;
    }

    /**
     * @param hourlyParkingCostTourDest
     *            the hourlyParkingCostTourDest to set
     */
    public void setHourlyParkingCostTourDest(float hourlyParkingCostTourDest)
    {
        this.hourlyParkingCostTourDest = hourlyParkingCostTourDest;
    }

    /**
     * @return the dailyParkingCostTourDest
     */
    public float getDailyParkingCostTourDest()
    {
        return dailyParkingCostTourDest;
    }

    /**
     * @param dailyParkingCostTourDest
     *            the dailyParkingCostTourDest to set
     */
    public void setDailyParkingCostTourDest(float dailyParkingCostTourDest)
    {
        this.dailyParkingCostTourDest = dailyParkingCostTourDest;
    }

    /**
     * @return the monthlyParkingCostTourDest
     */
    public float getMonthlyParkingCostTourDest()
    {
        return monthlyParkingCostTourDest;
    }

    /**
     * @param monthlyParkingCostTourDest
     *            the monthlyParkingCostTourDest to set
     */
    public void setMonthlyParkingCostTourDest(float monthlyParkingCostTourDest)
    {
        this.monthlyParkingCostTourDest = monthlyParkingCostTourDest;
    }

    /**
     * @return the tripOrigIsTourDest
     */
    public int getTripOrigIsTourDest()
    {
        return tripOrigIsTourDest;
    }

    /**
     * @param tripOrigIsTourDest
     *            the tripOrigIsTourDest to set
     */
    public void setTripOrigIsTourDest(int tripOrigIsTourDest)
    {
        this.tripOrigIsTourDest = tripOrigIsTourDest;
    }

    /**
     * @return the tripDestIsTourDest
     */
    public int getTripDestIsTourDest()
    {
        return tripDestIsTourDest;
    }

    /**
     * @param tripDestIsTourDest
     *            the tripDestIsTourDest to set
     */
    public void setTripDestIsTourDest(int tripDestIsTourDest)
    {
        this.tripDestIsTourDest = tripDestIsTourDest;
    }

    /**
     * @return the hourlyParkingCostTripOrig
     */
    public float getHourlyParkingCostTripOrig()
    {
        return hourlyParkingCostTripOrig;
    }

    /**
     * @param hourlyParkingCostTripOrig
     *            the hourlyParkingCostTripOrig to set
     */
    public void setHourlyParkingCostTripOrig(float hourlyParkingCostTripOrig)
    {
        this.hourlyParkingCostTripOrig = hourlyParkingCostTripOrig;
    }

    /**
     * @return the hourlyParkingCostTripDest
     */
    public float getHourlyParkingCostTripDest()
    {
        return hourlyParkingCostTripDest;
    }

    /**
     * @param hourlyParkingCostTripDest
     *            the hourlyParkingCostTripDest to set
     */
    public void setHourlyParkingCostTripDest(float hourlyParkingCostTripDest)
    {
        this.hourlyParkingCostTripDest = hourlyParkingCostTripDest;
    }

    /**
     * @return the outboundHalfTourDirection
     */
    public int getOutboundHalfTourDirection()
    {
        return outboundHalfTourDirection;
    }

    /**
     * @param outboundHalfTourDirection
     *            the outboundHalfTourDirection to set
     */
    public void setOutboundHalfTourDirection(int outboundHalfTourDirection)
    {
        this.outboundHalfTourDirection = outboundHalfTourDirection;
    }

    /**
     * @return the tourDepartPeriod
     */
    public int getTourDepartPeriod()
    {
        return tourDepartPeriod;
    }

    /**
     * @param tourDepartPeriod
     *            the tourDepartPeriod to set
     */
    public void setTourDepartPeriod(int tourDepartPeriod)
    {
        this.tourDepartPeriod = tourDepartPeriod;
    }

    /**
     * @param tourArrivePeriod
     *            the tourArrivePeriod to set
     */
    public void setTourArrivePeriod(int tourArrivePeriod)
    {
        this.tourArrivePeriod = tourArrivePeriod;
    }

    /**
     * @return the tourArrivePeriod
     */
    public int getTourArrivePeriod()
    {
        return tourArrivePeriod;
    }

    public double getNm_walkTime()
    {
        return nmWalkTime;
    }

    public void setNonMotorizedWalkTime(double nmWalkTime)
    {
        this.nmWalkTime = nmWalkTime;
    }

    public void setNonMotorizedBikeTime(double nmBikeTime)
    {
        this.nmBikeTime = nmBikeTime;
    }

    public double getNm_bikeTime()
    {
        return nmBikeTime;
    }

  
    public float getWorkTimeFactor() {
		return workTimeFactor;
	}

	public void setWorkTimeFactor(float workTimeFactor) {
		this.workTimeFactor = workTimeFactor;
	}

	public float getNonWorkTimeFactor() {
		return nonWorkTimeFactor;
	}

	public void setNonWorkTimeFactor(float nonWorkTimeFactor) {
		this.nonWorkTimeFactor = nonWorkTimeFactor;
	}

	public double getIvtCoeff() {
		return ivtCoeff;
	}

	public void setIvtCoeff(double ivtCoeff) {
		this.ivtCoeff = ivtCoeff;
	}

	public double getCostCoeff() {
		return costCoeff;
	}

    public double getWalkTransitLogsum() {
		return walkTransitLogsum;
	}

	public void setWalkTransitLogsum(double walkTransitLogsum) {
		this.walkTransitLogsum = walkTransitLogsum;
	}

	public double getPnrTransitLogsum() {
		return pnrTransitLogsum;
	}

	public void setPnrTransitLogsum(double pnrTransitLogsum) {
		this.pnrTransitLogsum = pnrTransitLogsum;
	}

	public double getKnrTransitLogsum() {
		return knrTransitLogsum;
	}

	public void setKnrTransitLogsum(double knrTransitLogsum) {
		this.knrTransitLogsum = knrTransitLogsum;
	}




	public int getAvAvailable() {
		return avAvailable;
	}

	public void setAvAvailable(int avAvailable) {
		this.avAvailable = avAvailable;
	}

	public float getWaitTimeTaxi() {
		return waitTimeTaxi;
	}

	public void setWaitTimeTaxi(float waitTimeTaxi) {
		this.waitTimeTaxi = waitTimeTaxi;
	}

	public float getWaitTimeSingleTNC() {
		return waitTimeSingleTNC;
	}

	public void setWaitTimeSingleTNC(float waitTimeSingleTNC) {
		this.waitTimeSingleTNC = waitTimeSingleTNC;
	}

	public float getWaitTimeSharedTNC() {
		return waitTimeSharedTNC;
	}

	public void setWaitTimeSharedTNC(float waitTimeSharedTNC) {
		this.waitTimeSharedTNC = waitTimeSharedTNC;
	}

	private void setupMethodIndexMap()
    {
        methodIndexMap = new HashMap<String, Integer>();

        methodIndexMap.put("getTourDepartPeriod", 0);
        methodIndexMap.put("getTourArrivePeriod", 1);
        methodIndexMap.put("getTripPeriod", 2);

        methodIndexMap.put("getWorkTour", 4);
        methodIndexMap.put("getOutboundStops", 5);
        methodIndexMap.put("getReturnStops", 6);
        methodIndexMap.put("getFirstTrip", 7);
        methodIndexMap.put("getLastTrip", 8);
        methodIndexMap.put("getTourModeIsDA", 9);
        methodIndexMap.put("getTourModeIsS2", 10);
        methodIndexMap.put("getTourModeIsS3", 11);
        methodIndexMap.put("getTourModeIsWalk", 12);
        methodIndexMap.put("getTourCrossingIsSentri", 13);
        methodIndexMap.put("getHourlyParkingCostTourDest", 14);
        methodIndexMap.put("getDailyParkingCostTourDest", 15);
        methodIndexMap.put("getMonthlyParkingCostTourDest", 16);
        methodIndexMap.put("getTripOrigIsTourDest", 17);
        methodIndexMap.put("getTripDestIsTourDest", 18);
        methodIndexMap.put("getHourlyParkingCostTripOrig", 19);
        methodIndexMap.put("getHourlyParkingCostTripDest", 20);
        
        methodIndexMap.put("getWorkTimeFactor", 50);
        methodIndexMap.put("getNonWorkTimeFactor", 51);

        methodIndexMap.put("getIvtCoeff", 60);
        methodIndexMap.put("getCostCoeff", 61);
               
        methodIndexMap.put("getWalkSetLogSum", 62);
        methodIndexMap.put("getPnrSetLogSum", 63);
        methodIndexMap.put("getKnrSetLogSum", 64);
        
        methodIndexMap.put("getWaitTimeTaxi", 70);
        methodIndexMap.put("getWaitTimeSingleTNC", 71);
        methodIndexMap.put("getWaitTimeSharedTNC", 72);

        methodIndexMap.put("getNm_walkTime", 90);
        methodIndexMap.put("getNm_bikeTime", 91);
        
        methodIndexMap.put("getAvAvailable", 95);

       
    }

    public double getValueForIndex(int variableIndex, int arrayIndex)
    {

        double returnValue = -1;

        switch (variableIndex)
        {
            case 0:
                returnValue = getTourDepartPeriod();
                break;
            case 1:
            	returnValue = getTourArrivePeriod();
                break;
            case 2:
            	returnValue = getTripPeriod();
            	break;
            case 4:
            	returnValue = getWorkTour();
                break;
            case 5:
            	returnValue = getOutboundStops();
                break;
            case 6:
            	returnValue = getReturnStops();
                break;
            case 7:
            	returnValue = getFirstTrip();
                break;
            case 8:
            	returnValue = getLastTrip();
                break;
            case 9:
            	returnValue = getTourModeIsDA();
                break;
            case 10:
            	returnValue = getTourModeIsS2();
                break;
            case 11:
            	returnValue = getTourModeIsS3();
                break;
            case 12:
            	returnValue = getTourModeIsWalk();
                break;
            case 13:
            	returnValue = getTourCrossingIsSentri();
                break;
            case 14:
            	returnValue = getHourlyParkingCostTourDest();
                break;
            case 15:
            	returnValue = getDailyParkingCostTourDest();
                break;
            case 16:
            	returnValue = getMonthlyParkingCostTourDest();
                break;
            case 17:
            	returnValue = getTripOrigIsTourDest();
                break;
            case 18:
            	returnValue = getTripDestIsTourDest();
                break;
            case 19:
            	returnValue = getHourlyParkingCostTripOrig();
                break;
            case 20:
            	returnValue = getHourlyParkingCostTripDest();
                break;
            case 50:
            	returnValue = getWorkTimeFactor();
                break;
            case 51:
            	returnValue = getNonWorkTimeFactor();
            	break;
            case 60:
            	returnValue = getIvtCoeff();
                break;
            case 61:
            	returnValue = getCostCoeff();
                break;
            case 62:
                returnValue = getWalkTransitLogsum();
                break;
            case 63:
            	returnValue = getPnrTransitLogsum();
                break;
            case 64:
            	returnValue = getKnrTransitLogsum();
                break;
            case 70: return getWaitTimeTaxi();
            case 71: return getWaitTimeSingleTNC();
            case 72: return getWaitTimeSharedTNC();

            case 90:
            	returnValue = getNm_walkTime();
            	break;
            case 91:
            	returnValue = getNm_bikeTime();
                break;
            case 95:
            	returnValue = getAvAvailable();
            	break;
            	
            default:
                logger.error( "method number = " + variableIndex + " not found" );
                throw new RuntimeException( "method number = " + variableIndex + " not found" );
        }
        return returnValue;
    
    }

    public int getIndexValue(String variableName)
    {
        return methodIndexMap.get(variableName);
    }

    public int getAssignmentIndexValue(String variableName)
    {
        throw new UnsupportedOperationException();
    }

    public double getValueForIndex(int variableIndex)
    {
        throw new UnsupportedOperationException();
    }

    public void setValue(String variableName, double variableValue)
    {
        throw new UnsupportedOperationException();
    }

    public void setValue(int variableIndex, double variableValue)
    {
        throw new UnsupportedOperationException();
    }

}