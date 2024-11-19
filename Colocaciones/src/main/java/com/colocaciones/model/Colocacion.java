package com.colocaciones.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "COLOCACIONES")
@Entity
public class Colocacion implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FECHA_ CORTE")
	private LocalDateTime fechaCorte;
	
	@Column(name = "DPT_ID")
	private String dptId;
	
	@Column(name = "DPT_NOMBRE")
	private String dptNombre;
	
	@Column(name = "MPO_ID")
	private String mpoId;
	
	@Column(name = "MPO_NOMBRE")
	private String mpoNombre;
	
	@Column(name = "CIIU_COD")
	private String ciiuCod;
	
	@Column(name = "CIIU_NOMBRE")
	private String ciiuNombre;
	
	@Column(name = "DEN_ID")
	private String denId;
	
	@Column(name = "IND_NOMBRE")
	private String indNombre;
	
	@Column(name = "OCU_ID")
	private String ocuId;
	
	@Column(name = "OCUPACION")
	private String ocupacion;
	
	@Column(name = "GENERO")
	private String genero;
	
	@Column(name = "ROM")
	private Boolean rom;
	
	@Column(name = "INDIGENAS")
	private Boolean indigenas;
	
	@Column(name = "AFROCOLOMBIANO")
	private Boolean afrocolombiano;
	
	@Column(name = "POBLACION_PALENQUE")
	private Boolean poblacionPalenque;
	
	@Column(name = "RAIZALES")
	private Boolean raizales;
	
	@Column(name = "DISCAPACIDAD_PSICOSO")
	private Boolean discapacidadPsicoso;
	
	@Column(name = "DISCAPACIDAD_AUDITIVA")
	private Boolean discapacidadAuditiva;
	
	@Column(name = "DISCAPACIDAD_SORDOCEG")
	private Boolean discapacidadSordoceg;
	
	@Column(name = "DISCAPACIDAD_FISI")
	private Boolean discapacidadFisi;
	
	@Column(name = "DISCAPACIDAD_VISUAL")
	private Boolean discapacidadVisual;
	
	@Column(name = "DISCAPACIDAD_MULTIPLE")
	private Boolean discapacidadMultiple;
	
	@Column(name = "DISCAPACIDAD_INTELECT")
	private Boolean discapacidadIntelect;
	
	@Column(name = "EDAD")
	private int edad;
	
	@Column(name = "SUELDO_MES")
	private String sueldoMes;
	
	@Column(name = "TOTAL_COLOCA")
	private Double totalColoca;
	
	@Column(name = "FECHA CARGUE")
	private LocalDateTime fechaCargue;
	
	//@Column(name = "SSMA_TimeStamp")
	//private LocalDateTime ssmaTimeStamp;
}
