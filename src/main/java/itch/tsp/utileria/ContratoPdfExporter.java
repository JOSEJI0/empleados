package itch.tsp.utileria;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import itch.tsp.model.Contrato;
import jakarta.servlet.http.HttpServletResponse;

public class ContratoPdfExporter {

	private Contrato contrato;

	private final String RUTA_IMAGENES = "./imagenes/empleados/";

	public ContratoPdfExporter(Contrato contrato) {
		this.contrato = contrato;
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		// --- 1. DEFINICIÓN DE FUENTES Y COLORES ---
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(33, 37, 41));
		Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new Color(13, 110, 253));
		Font fuenteCuerpo = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);

		// --- 2. ENCABEZADO ---
		Paragraph titulo = new Paragraph("EXPEDIENTE DE CONTRATO LABORAL", fuenteTitulo);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		titulo.setSpacingAfter(5);
		documento.add(titulo);

		Paragraph folio = new Paragraph("Folio N°: " + contrato.getId() + " | Sistema TSPW",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Color.GRAY));
		folio.setAlignment(Paragraph.ALIGN_CENTER);
		folio.setSpacingAfter(25);
		documento.add(folio);

		// --- 3. SECCIÓN 1: DATOS DEL EMPLEADO (CON FOTO) ---
		Paragraph sub1 = new Paragraph("I. DATOS DEL TRABAJADOR", fuenteSubtitulo);
		sub1.setSpacingAfter(10);
		documento.add(sub1);

		// Tabla principal
		PdfPTable tablaSeccion1 = new PdfPTable(2);
		tablaSeccion1.setWidthPercentage(100);
		tablaSeccion1.setWidths(new float[] { 2.5f, 7.5f }); // 25% para la foto, 75% para los datos
		tablaSeccion1.setSpacingAfter(20);

		// ---> A. Columna de la Foto
		PdfPCell celdaFoto = new PdfPCell();
		celdaFoto.setBorder(0);
		celdaFoto.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaFoto.setVerticalAlignment(Element.ALIGN_MIDDLE);

		boolean tieneFoto = contrato.getEmpleado() != null && contrato.getEmpleado().getFoto() != null
				&& !contrato.getEmpleado().getFoto().equals("no-imagen.jpg")
				&& !contrato.getEmpleado().getFoto().isEmpty();

		if (tieneFoto) {
			try {
				String rutaAbsoluta = RUTA_IMAGENES + contrato.getEmpleado().getFoto();
				File archivoFisico = new File(rutaAbsoluta);

				if (archivoFisico.exists()) {
					Image img = Image.getInstance(rutaAbsoluta);
					img.scaleToFit(90, 90); // Ajustamos el tamaño de la foto
					img.setAlignment(Element.ALIGN_CENTER);
					celdaFoto.addElement(img);
				} else {
					celdaFoto.addElement(new Paragraph("[Foto no encontrada en disco]", fuenteCuerpo));
				}
			} catch (Exception e) {
				celdaFoto.addElement(new Paragraph("[Error al cargar foto]", fuenteCuerpo));
			}
		} else {
			// Si no tiene foto, dejamos un recuadro o texto vacío
			celdaFoto.addElement(new Paragraph("Sin fotografía\nregistrada",
					FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, Color.GRAY)));
		}
		tablaSeccion1.addCell(celdaFoto);

		// ---> B. Columna de los Datos (Tabla Anidada)
		PdfPTable tablaDatosPersonales = new PdfPTable(2);
		tablaDatosPersonales.setWidthPercentage(100);
		tablaDatosPersonales.setWidths(new float[] { 4f, 6f });

		String nombreCompleto = (contrato.getEmpleado() != null)
				? contrato.getEmpleado().getNombre() + " " + contrato.getEmpleado().getApellidos()
				: "Sin asignar";
		String depto = (contrato.getEmpleado() != null && contrato.getEmpleado().getDepartamento() != null)
				? contrato.getEmpleado().getDepartamento().getNombre()
				: "Sin área asignada";

		agregarCelda(tablaDatosPersonales, "Nombre Completo:", nombreCompleto, fuenteCuerpo);
		agregarCelda(tablaDatosPersonales, "NSS:", contrato.getNoSeguroSocial(), fuenteCuerpo);
		agregarCelda(tablaDatosPersonales, "Departamento:", depto, fuenteCuerpo);

		PdfPCell celdaDatos = new PdfPCell(tablaDatosPersonales);
		celdaDatos.setBorder(0); // Quitamos el borde exterior para que se vea limpio
		tablaSeccion1.addCell(celdaDatos);

		// Agregamos la estructura completa al documento
		documento.add(tablaSeccion1);

		// --- 4. SECCIÓN 2: CONDICIONES DEL CONTRATO ---
		Paragraph sub2 = new Paragraph("II. CONDICIONES LABORALES", fuenteSubtitulo);
		sub2.setSpacingAfter(10);
		documento.add(sub2);

		PdfPTable tablaContrato = new PdfPTable(2);
		tablaContrato.setWidthPercentage(100);
		tablaContrato.setWidths(new float[] { 3.5f, 6.5f });
		tablaContrato.setSpacingAfter(20);

		String fechaIngresoStr = "No registrada";
		if (contrato.getEmpleado() != null && contrato.getEmpleado().getFechaIngreso() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "MX"));
			fechaIngresoStr = sdf.format(contrato.getEmpleado().getFechaIngreso());
		}

		String salarioStr = "$0.00";
		if (contrato.getEmpleado() != null && contrato.getEmpleado().getSalario() != null) {
			salarioStr = String.format("$%,.2f MXN", contrato.getEmpleado().getSalario());
		}

		agregarCelda(tablaContrato, "Tipo de Vinculación:", contrato.getTipoContrato(), fuenteCuerpo);
		agregarCelda(tablaContrato, "Fecha de Ingreso:", fechaIngresoStr, fuenteCuerpo);
		agregarCelda(tablaContrato, "Salario Mensual Bruto:", salarioStr, fuenteCuerpo);

		documento.add(tablaContrato);

		// --- 5. CLAÚSULAS / TEXTO LEGAL (Adaptado formal) ---

		String textoLegal = "El presente documento constituye una representación impresa del expediente electrónico resguardado en el Sistema de Gestión. "
				+ "El trabajador arriba citado manifiesta su plena conformidad con las condiciones salariales, la adscripción al departamento "
				+ "y el esquema de vinculación laboral convenidos. En consecuencia, ambas partes reconocen la presente constancia como el "
				+ "testimonio legal que acredita la existencia y vigencia de la relación de trabajo.";

		Paragraph clausulas = new Paragraph(textoLegal, fuenteCuerpo);
		clausulas.setAlignment(Element.ALIGN_JUSTIFIED);
		clausulas.setSpacingBefore(15); // Un poco más de margen superior para separar
		clausulas.setSpacingAfter(70); // Espacio suficiente para las firmas físicas

		documento.add(clausulas);

		// --- 6. FIRMAS ---
		PdfPTable tablaFirmas = new PdfPTable(2);
		tablaFirmas.setWidthPercentage(100);

		PdfPCell celdaEmpleadoFirma = new PdfPCell(
				new Phrase("_____________________________\nFirma del Trabajador\n" + nombreCompleto, fuenteCuerpo));
		celdaEmpleadoFirma.setBorder(0);
		celdaEmpleadoFirma.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell celdaEmpresaFirma = new PdfPCell(new Phrase(
				"_____________________________\nPor la Empresa\nDepartamento de Recursos Humanos", fuenteCuerpo));
		celdaEmpresaFirma.setBorder(0);
		celdaEmpresaFirma.setHorizontalAlignment(Element.ALIGN_CENTER);

		tablaFirmas.addCell(celdaEmpleadoFirma);
		tablaFirmas.addCell(celdaEmpresaFirma);

		documento.add(tablaFirmas);

		documento.close();
	}

	private void agregarCelda(PdfPTable tabla, String etiqueta, String valor, Font fuenteValor) {
		PdfPCell celdaEtiqueta = new PdfPCell(
				new Phrase(etiqueta, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
		celdaEtiqueta.setBackgroundColor(new Color(245, 245, 245));
		celdaEtiqueta.setPadding(8f);
		celdaEtiqueta.setBorderColor(new Color(200, 200, 200));
		tabla.addCell(celdaEtiqueta);

		PdfPCell celdaValor = new PdfPCell(new Phrase(valor, fuenteValor));
		celdaValor.setPadding(8f);
		celdaValor.setBorderColor(new Color(200, 200, 200));
		tabla.addCell(celdaValor);
	}
}