package com.cvte.entity;
/**
* @author jan
* @data 2018年8月4日 上午11:26:51
*/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="hospital")
public class HospitalDescBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 267223267480895633L;
	private int id;  // 医院id
	private int terminalId;  // 该医院终端设备号(假设该医院只有一台设备)
    private String hospitalName;
    private String address;
    private String phone;
    private int layer;  // 医院等级
    private int subLayer;
    private String url;  // 图片地址
    private String description;  // 医院简介
    private double longitude;  // 经度
    private double dimension; // 维度
    private Date addTime;
    private int deleted;

    public HospitalDescBean(
            int id, int terminalId, String hospitalName, String address, String phone,
            int layer, String url, String description,
            double longitude, double dimension) {
        this.id = id;
        this.terminalId = terminalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.phone = phone;
        this.layer = layer;
        this.url = url;
        this.description = description;
        this.longitude = longitude;
        this.dimension = dimension;
    }

    public HospitalDescBean() {
    }

    @Column(name="terminal_id")
    public int getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(int terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "phone")
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="deleted")
    public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Column(name = "sublayer")
    public int getSubLayer() {
        return subLayer;
    }

    public void setSubLayer(int subLayer) {
        this.subLayer = subLayer;
    }

    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "layer")
    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Column(name = "img_url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "dimension")
    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    @Column(name="add_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", address='" + address + '\'' +
                ", layer='" + layer + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", dimension=" + dimension +
                '}';
    }
}

