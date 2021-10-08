import { Injectable } from '@angular/core';
import { Workbook } from 'exceljs';
import * as fs from 'file-system';

@Injectable()

export class ExcelService {
  
    constructor() { }
     
    generateExcel(data: any[]){

      const title = 'Pedidos Quincena'; 
      const header = ["Aliado", "Domiciliario", "Direccion", "Observacion", "Valor"];

      let workbook = new Workbook();
      let worksheet = workbook.addWorksheet("Consolidado");
      let headerRow = worksheet.addRow(header);
      
      headerRow.eachCell((cell, number) => {
        cell.fill = {
          type: 'pattern',
          pattern: 'solid',
          fgColor: {argb: 'FFFFFF00'},
          bgColor: {argb: 'FF0000FF'}
        }

        cell.border = { top: {style: 'thin'}, left: {style: 'thin'}, bottom: {style: 'thin'}, right: {style: 'thin'} }
      });

      worksheet.addRows(data);

      workbook.xlsx.writeBuffer().then((data) => {
        let blob = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
        fs.saveAs(blob, 'pedidos.xlsx');
      })
    
    }

    
}
  