import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ComplianceService, ComplianceTransaction } from './services/compliance.service';

@Component({
  selector: 'app-root',
  standalone: true,
  // Importing common direct structural directives and forms binding into our standalone tree
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  // Two-way form binding state fields mapping to our visual inputs
  mockClientName: string = '';
  mockTechStack: string = '';
  mockValue: number | null = null;
  mockPainPoint: string = '';

  // Application state controllers
  isLoading: boolean = false;
  processedTransaction: ComplianceTransaction | null = null;

  // Dependency Injection: Injecting our singleton service through the class constructor
  constructor(private complianceService: ComplianceService) {}

  /**
   * Click Event Handler: Executes the inbound Apex REST mock webhook sequence
   */
  fireTransactionSimulation(): void {
    if (!this.mockClientName || !this.mockPainPoint) {
      alert('⚠️ Architecture Rule: Client Name and Latency Anomaly fields are required.');
      return;
    }

    this.isLoading = true;

    // Formatting our clean data payload match model structure
    const payload: ComplianceTransaction = {
      companyName: this.mockClientName,
      currentTechStack: this.mockTechStack || 'Standard Framework Node',
      painPointDescription: this.mockPainPoint,
      estimatedDealValue: this.mockValue || 0
    };

    console.log('📡 UI Controller: Dispatching mock Apex payload to gateway...', payload);

    // Subscribing to our HttpClient Observable stream pipeline
    this.complianceService.simulateSalesforceWebhook(payload).subscribe({
      next: (response) => {
        console.log('✅ Ingestion Gateway Ack: Target persisted successfully.', response);
        this.processedTransaction = response;
        this.isLoading = false;
        this.clearForm();
      },
      error: (err) => {
        console.error('❌ Pipeline connectivity break: Check backend server status.', err);
        
        // High-fidelity fallback state display simulation in case local cross-network ports are blocked
        this.processedTransaction = {
          ...payload,
          aiStrategySummary: 'Simulated Risk Resolution Framework:\n• Isolate legacy Oracle DB Queue latency pools by introducing an asynchronous AWS SQS messaging buffer tier.\n• Migrate high-velocity transaction tables to fully decoupled MongoDB Atlas NoSQL collection clusters to eliminate indexing blocks.'
        };
        this.isLoading = false;
      }
    });
  }

  /**
   * Helper utility to clear fields out after submission
   */
  private clearForm(): void {
    this.mockClientName = '';
    this.mockTechStack = '';
    this.mockValue = null;
    this.mockPainPoint = '';
  }
}
