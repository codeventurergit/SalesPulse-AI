import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ComplianceTransaction {
  id?: string;
  companyName: string;
  currentTechStack: string;
  painPointDescription: string;
  estimatedDealValue: number;
  aiStrategySummary?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ComplianceService {
  
  private apiBaseUrl = 'http://localhost:8080/api/v1/salesforce';

  constructor(private http: HttpClient) {}

  simulateSalesforceWebhook(payload: ComplianceTransaction): Observable<ComplianceTransaction> {
    return this.http.post<ComplianceTransaction>(`${this.apiBaseUrl}/webhook`, payload);
  }
}
