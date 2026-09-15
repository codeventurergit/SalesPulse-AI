# SalesPulse AI: Real-Time B2B Institutional Transaction Ingestion & Compliance Risk Architecture

A production-grade, event-driven multi-agent enterprise pipeline designed to ingest high-velocity B2B financial transaction records, isolate pipeline latency anomalies, and orchestrate serverless Retrieval-Augmented Generation (RAG) loops to synthesize instant risk mitigation roadmaps.

## 🗺️ System Architecture & Design Patterns

[ Salesforce CRM ] ──(Apex REST Callout)──► [ Spring Boot Inbound Adapter ]│(Pipes & Filters Event Sink)▼[ Angular 17 UI ] ◄──(Reactive Telemetry)── [ MongoDB NoSQL ] ◄── [ Databricks / PySpark Stream ]│(AWS Bedrock RAG Engine)


### 🧠 Core Architectural Technical Pillars

*   **Inbound Adapter Pattern (Spring Boot):** Acts as a high-performance REST Gateway to decouple ingestion traffic. It processes incoming Salesforce JSON contracts organically, instantly persisting state to the database and dropping isolated transaction logs into a streaming directory, returning an immediate `200 OK` handshake response to prevent pipeline blocks.
*   **Distributed Stream Processing (PySpark & Databricks Compute):** Leverages a Kappa-style streaming computation engine to process incoming event files in parallel across cloud server nodes rather than linearly, optimizing throughput for high-velocity data pools.
*   **Serverless Contextual RAG Orchestration (AWS Bedrock & MongoDB Atlas):** Implements an advanced two-step AI evaluation loop. First, it runs a mathematical vector similarity lookup to pull relevant compliance reference guidelines out of MongoDB. Second, it wraps both the transaction variables and the fetched data into a strict chat prompt template before invoking serverless enterprise foundational models (**Anthropic Claude v2 / Amazon Nova**) at a controlled temperature (`0.3`) for deterministic, low-hallucination mitigation strategies.
*   **Modern Standalone User Interface (Angular 17+):** Built using an optimized standalone component configuration (bypassing heavy, outdated NgModule boilerplate files) and utilizing the Singleton Service Pattern to handle decoupled HTTP communication streams seamlessly.

## 🛠️ Technology Stack Matrix
*   **Backend Core:** Java 17, Spring Boot 3.3.3, Maven, Spring Data MongoDB
*   **Data & AI Layer:** PySpark Structured Streaming, Databricks Compute, AWS Bedrock Runtime SDK, Boto3
*   **Database Infrastructure:** MongoDB Atlas Cloud NoSQL (MongoDB Vector Search)
*   **Frontend Ecosystem:** Angular 17+, TypeScript, HTML5 semantic layouts, Modern CSS Flexbox grid structures

## 🔒 Production Security & Cost Compliance
*   **Externalized Infrastructure Configurations:** Zero credentials or cloud database passwords are leaked into source control. All cluster endpoints are dynamically managed via temporary, secure Linux Environment Variables (`${MONGO_URI}`).
*   **Cloud Token & Cost Management:** The AI orchestration layer strictly manages character outputs using an explicit token cap framework (`max_tokens_to_sample: 300`) to guarantee fast response summaries while actively eliminating cloud runtime budget waste.

