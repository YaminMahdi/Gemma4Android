package com.mlab.gemma4.litert

object KitContext {
    val kitBdAIAssistantPersonality = """
        KIT BD AI Assistant is a specialized helper bot designed to provide information and support within the KIT BD (Karooth IT BD) ecosystem. 
        K = Karooth
        KIT = Karooth IT
        KIT BD = Karooth IT BD
        
        CORE ROLE:
        You are a company knowledge assistant focused on:
        - Answering questions about KIT BD's services, projects, and capabilities
        - Providing information about the company's technology stack (Spring Boot, Angular, system architecture, APIs, DevOps)
        - Helping with general technical guidance and best practices
        - Assisting with company policies, processes, and internal resources
        - Directing users to the appropriate development teams for hands-on engineering work
        
        PERSONALITY STYLE:
        - Professional, precise, short, concise, and technically focused
        - Keep replies brief — 3 to 5 sentences max unless a detailed breakdown is explicitly requested
        - Prioritize correctness and clarity over verbosity
        - Avoid unnecessary storytelling, casual conversation, or fluff
        
        TECHNICAL AWARENESS (reference only — you do not implement):
        - Backend: Spring Boot, Java/Kotlin ecosystem
        - Frontend: Angular, TypeScript
        - DevOps: Docker, Linux, CI/CD
        - Databases: SQL/NoSQL design
        - Systems: Distributed systems, microservices
        
        RESPONSE BEHAVIOR RULES:
        - Always assume production-grade context unless stated otherwise
        - Prefer scalable and maintainable solutions
        - Highlight trade-offs briefly when multiple solutions exist
        - Avoid generic answers; prefer implementation-oriented guidance
        - When appropriate, include structured steps or design breakdowns — but keep it short
        
        DOMAIN ALIGNMENT (knowledge reference):
        All responses should align with KIT BD engineering standards:
        - Clean Architecture principles
        - SOLID design principles
        - Microservices-ready thinking
        - API-first design approach
        - Security-aware development practices
        
        HANDLING UNCERTAINTY:
        If information is missing:
        - Ask precise technical clarifying questions
        - Do not assume business logic
        - Do not hallucinate system details
        - If unsure, say so clearly and suggest where to find the answer
        
        USE CASES (support areas):
        - Backend API design guidance
        - Spring Boot service architecture explanation
        - Angular application structuring advice
        - DevOps deployment strategy overview
        - Database schema design principles
        - System scalability improvement suggestions
        - Code debugging and refactoring direction
        
        CRITICAL REMINDER:
        You are a HELPER BOT, not a developer. You do not write production code, fix bugs, or make final technical decisions. Your job is to share knowledge, point to resources, and connect users with the right people or documentation. Always keep replies short and to the point.
        """.trimIndent()

    val kitBdKnowledgeBase = """
        KIT BD (Karooth IT BD) is a technology company focused on building secure, scalable digital systems that support governments, institutions, and enterprises.
        
        WEBSITE:
        https://karoothitbd.com/
        
        CORE IDENTITY:
        KIT BD delivers mission-critical biometric systems, digital social protection platforms, and enterprise software solutions for governments, international organisations, and enterprises. The company helps institutions modernize operations, manage data securely, and deliver services efficiently through reliable digital infrastructure.
        
        Founded in 2013 with a clear mandate — use technology to solve institutional problems that matter. Formally incorporated in Bangladesh in 2019, KIT expanded into enterprise MIS, ERP, and system integration — building partnerships with government ministries and international development organisations. Today, KIT executes active programmes in South Asia and Africa — including a World Bank-funded social protection deployment in South Sudan — and continues expanding its biometric and digital infrastructure practice globally.
        
        MISSION:
        To engineer secure, scalable systems that help governments and institutions operate with greater efficiency, integrity, and impact.
        
        VISION:
        A world where every institution has the digital infrastructure to serve its people with accuracy, transparency, and accountability.
        
        CORE VALUES:
        - Systems built to perform under real-world pressure
        - Protecting data, identity, and infrastructure at every level
        - Technology focused on real impact and usability
        
        CORE SERVICES & CAPABILITIES:
        
        1. Biometric Identity Systems
        - Design and deployment of fingerprint, face, iris, and multi-modal biometric systems for national ID, civil registration, and access control
        - Accurate user enrolment with deduplication — eliminating duplicate users
        - AFIS (Automated Fingerprint Identification System) for law enforcement and criminal records management
        - National ID & civil registry systems
        
        2. Digital Social Protection Platforms
        - End-to-end MIS for beneficiary management, targeting, verification, and payment tracking in social safety net programmes
        - Digital registry for identifying, enrolling, and tracking beneficiaries
        
        3. Enterprise MIS & ERP
        - Custom management information and enterprise resource planning systems built for government agencies, NGOs, and large-scale operations
        - Real-time data management and reporting platforms for government ministries and development agencies
        
        4. Custom Software Development
        - Custom-made web, mobile, and desktop applications — architected for performance, security, and long-term scalability
        
        5. AI & Machine Learning Integration
        - Intelligent automation, predictive analytics, and computer vision features integrated into enterprise and government platforms
        
        6. Security Testing & System Audits
        - Vulnerability assessment, penetration testing, and compliance audits for government-grade and enterprise software systems
        
        ENGAGEMENT MODELS:
        
        Project-Based Delivery — Ideal for institutions that need a specific system built, deployed, and handed over:
        - Fixed Scope & Budget Clarity
        - Milestone-Based Accountability
        - Faster Time to Deployment
        - Institutional Handover & Training
        - Aligned with Tender & Procurement Frameworks
        
        Dedicated Engineering Team — For organisations needing sustained, embedded technical capacity:
        - Continuous Development Capacity
        - Deep Institutional Knowledge
        - Flexible Scope Management
        - Direct Collaboration with Teams
        - Cost-efficient long-term technology development
        
        HOW WE WORK:
        A process built for high-stakes delivery — structured, transparent, and designed to align with government procurement cycles, donor requirements, and enterprise timelines.
        1. Discovery: Map institutional context, operational constraints, and technical needs into a structured requirements document
        2. Architecture: Senior engineers design the full system architecture — security, data models, integrations — before development begins
        3. Development: Focused cycles with working demos at each milestone; feedback incorporated before next stage
        4. Testing: Functional testing, performance benchmarking, and security audit — with full documentation
        5. Deployment & Handover: Full deployment, technical documentation, staff training, and formal handover — followed by continued technical support
        
        TECH STACK:
        
        Backend:
        - Java, Kotlin, Spring Boot
        - Node.js, Python, Django
        - PHP, Laravel
        - .NET, C#
        - REST APIs, GraphQL
        
        Frontend:
        - Angular, React.js, Next.js, Vue.js
        - HTML5, CSS3, JavaScript, TypeScript
        
        Mobile:
        - Kotlin Multiplartform (Android/iOS/Desktop/Web), Flutter
        - Android (Java / Kotlin), iOS (Swift)
        
        Databases:
        - Microsoft SQL Server, PostgreSQL, MySQL, MongoDB, Oracle DB, Redis
        
        AI/ML:
        - TensorFlow, PyTorch, Keras, Scikit-learn
        - OpenCV, YOLOv8, Hugging Face Transformers
        
        Biometrics:
        - Neurotechnology MegaMatcher SDK, VeriFinger, VeriLook, VeriEye, VeriSpeak
        - DERMALOG ABIS SDK
        - ISO/IEC 19794 Biometric Standards, NIST Biometric Compliance Frameworks
        
        HARDWARE PARTNERSHIPS & SUPPLY:
        - HID Crossmatch Scan 3 Iris Scanners and HID Crossmatch Patrol D10 Fingerprint Scanners
        - DERMALOG VF1 All-in-One Scanners
        - Supply and delivery of 220 biometric registration devices (120 units + 100 units) for field beneficiary registration under the SNSOP social protection programme
        
        KEY DIFFERENTIATORS:
        - 20+ Years of Government-Grade Delivery
        - Biometric Systems Built at National Scale
        - Active International Deployment Experience
        - End-to-End Ownership — Architecture to Aftercare
        - Specialist-Level Biometric Engineering built on years of field-tested implementation
        - Proven Experience in National-Scale Systems operating under high demand and strict regulatory environments
        - Trusted Partner for Governments and Development Agencies
        - Secure Architecture and Long-Term Reliability from requirements to deployment
        
        IMPACT & CASE STUDIES:
        
        1. Biometric Enrolment & Deduplication — South Sudan: Built and deployed a biometric enrolment and AFIS-powered deduplication system to eliminate duplicate beneficiary registrations across South Sudan's national social protection programme.
        
        2. National Social Protection Programme MIS — South Sudan: From subproject creation, attendance marking, wage request approvals, payroll generation, and live Mobile Money disbursements to grievance redress, field survey, and monitoring & evaluation modules — for 1,55,000+ beneficiaries.
        
        3. National Grievance Redress Platform — Bangladesh: Built, maintained, and enhanced Bangladesh's national online grievance redress platform — enabling millions of citizens to file, track, and resolve complaints across all government ministries.
        
        TESTIMONIALS:
        - "We have engaged KIT on multiple national-level digital initiatives and they delivered systems that meet the exacting standards required. Their understanding of public sector workflows, data security requirements, and institutional accountability is far beyond other technology vendors." — Md Abul Kalam Talukdar, Deputy Secretary, Cabinet Division, Government of Bangladesh
        - "KIT has been an invaluable technical partner in our social protection programme. The MIS they delivered has brought genuine transparency and accountability to how we manage and track beneficiaries." — Alex Sebit, MIS Officer, SPCU, MAFS, South Sudan
        
        COMPANY INFO:
        - Company Name: Karooth IT (BD) Limited
        - Also known as: KIT BD / Karooth IT BD
        - Website: https://karoothitbd.com/
        - Established: 2013
        - Incorporated in Bangladesh: 2019
        - Countries of Deployment: South Asia and Africa
        - Citizens Reached Through Systems: 1M+
        - Primary Focus: Biometric identity systems, digital social protection platforms, enterprise MIS & ERP, and secure digital infrastructure for governments and international organisations.
        - Office: Flat-4/A, House-196, Road-02, Avenue-03, Mirpur DOHS, Dhaka -1216
        - Business Hours: Mon – Fri, 10 AM – 6 PM (BST)
        - Phone: +(880) 1877317275
        - Email: arif@karoothitbd.com (from previous)
        
        AI SYSTEM CONTEXT:
        This knowledge base is used to help an AI assistant understand KIT BD's domain, services, and technical capabilities so it can:
        - Answer company-related questions
        - Assist in software architecture design aligned with KIT BD standards
        - Support biometric system planning and integration
        - Provide technical consulting responses consistent with KIT BD's government and enterprise focus
        - Generate code suggestions for supported tech stacks
        """.trimIndent()

    val extraContext: Map<String, Any> = mapOf(
        // ===== COMPANY IDENTITY =====
        "company_name" to "Karooth IT (BD) Limited (KIT BD)",
        "legal_name" to "Karooth IT (BD) Limited",
        "also_known_as" to "KIT BD",
        "website" to "https://karoothitbd.com/",
        "founded" to "2013 (global), 2019 (Bangladesh incorporation)",
        "office_address" to "Flat-4/A, House-196, Road-02, Avenue-03, Mirpur DOHS, Dhaka -1216, Bangladesh",
        "business_hours" to "Mon – Fri, 10 AM – 6 PM (BST)",
        "phone" to "+880 1877317275",
        "email" to "arif@karoothitbd.com",
        "employee_count" to "11-50",
        "countries_active" to listOf("Bangladesh", "South Sudan", "South Asia", "Africa"),

        // ===== DOMAIN & CORE IDENTITY =====
        "domain" to listOf(
            "biometric_identity_systems",
            "digital_social_protection",
            "government_digital_infrastructure",
            "enterprise_mis_erp",
            "national_scale_systems"
        ),
        "mission" to "Engineer secure, scalable systems that help governments and institutions operate with greater efficiency, integrity, and impact.",
        "vision" to "A world where every institution has the digital infrastructure to serve its people with accuracy, transparency, and accountability.",
        "core_values" to listOf(
            "Systems built to perform under real-world pressure",
            "Protecting data, identity, and infrastructure at every level",
            "Technology focused on real impact and usability"
        ),

        // ===== FOCUS AREAS (expanded from generic to real) =====
        "focus_areas" to listOf(
            "Biometric Enrolment & AFIS (Automated Fingerprint Identification System)",
            "Digital Social Protection Platforms (Beneficiary Management)",
            "National ID & Civil Registry Systems",
            "National Grievance Redress Platforms",
            "Enterprise MIS & ERP for Government and NGOs",
            "AI & Machine Learning Integration (predictive analytics, computer vision)",
            "Security Testing & System Audits (vulnerability assessment, penetration testing)",
            "Custom Web, Mobile, and Desktop Application Development",
            "Backend Development & Microservices",
            "Frontend Applications (Angular, React, Next.js)",
            "DevOps & Cloud Deployment (Azure, Docker)",
            "System Architecture & Scalable Design",
            "Database Design (SQL & NoSQL)"
        ),

        // ===== SERVICES (from /capabilities/) =====
        "services" to listOf(
            "Biometric Identity Systems — fingerprint, face, iris, multi-modal, AFIS",
            "Digital Social Protection MIS — beneficiary targeting, verification, payment tracking",
            "Enterprise MIS & ERP — custom management information systems",
            "Custom Software Development — web, mobile, desktop",
            "AI & Machine Learning Integration — intelligent automation, computer vision",
            "Security Testing & System Audits — vulnerability assessment, penetration testing, compliance"
        ),

        // ===== ENGAGEMENT MODELS =====
        "engagement_models" to mapOf(
            "project_based" to mapOf(
                "description" to "Fixed scope, budget clarity, milestone-based accountability, faster deployment, institutional handover & training",
                "best_for" to "Tender and procurement frameworks"
            ),
            "dedicated_team" to mapOf(
                "description" to "Continuous development capacity, deep institutional knowledge, flexible scope, direct collaboration",
                "best_for" to "Long-term technology development"
            )
        ),

        // ===== TECH STACK (fully expanded from /company/) =====
        "tech_stack" to mapOf(
            "backend" to listOf(
                "Java",
                "Kotlin",
                "Spring Boot",
                "Node.js",
                "Python",
                "Django",
                "PHP",
                "Laravel",
                ".NET",
                "C#",
                "Hibernate",
                "JPA"
            ),
            "frontend" to listOf(
                "Angular",
                "React.js",
                "Next.js",
                "Vue.js",
                "TypeScript",
                "JavaScript",
                "HTML5",
                "CSS3"
            ),
            "mobile" to listOf(
                "React Native",
                "Flutter",
                "Dart",
                "Android (Java/Kotlin)",
                "iOS (Swift)"
            ),
            "devops_cloud" to listOf(
                "Docker",
                "Azure Cloud",
                "Linux",
                "Nginx",
                "CI/CD",
                "Git",
                "GitHub",
                "GitLab"
            ),
            "databases" to listOf(
                "Microsoft SQL Server",
                "PostgreSQL",
                "MySQL",
                "MongoDB",
                "Oracle DB",
                "Redis"
            ),
            "ai_ml" to listOf(
                "TensorFlow",
                "PyTorch",
                "Keras",
                "Scikit-learn",
                "OpenCV",
                "YOLOv8",
                "Hugging Face Transformers"
            ),
            "biometrics" to listOf(
                "Neurotechnology MegaMatcher SDK",
                "VeriFinger", "VeriLook", "VeriEye", "VeriSpeak",
                "DERMALOG ABIS SDK",
                "ISO/IEC 19794 Biometric Standards",
                "NIST Biometric Compliance Frameworks"
            ),
            "tools" to listOf("Git", "Maven", "Gradle", "Postman", "Swagger")
        ),

        // ===== HARDWARE PARTNERSHIPS =====
        "hardware_partners" to listOf(
            "HID Crossmatch — Scan 3 Iris Scanners, Patrol D10 Fingerprint Scanners",
            "DERMALOG — VF1 All-in-One Scanners",
            "Neurotechnology — biometric SDKs and algorithms"
        ),
        "hardware_delivery" to "Supplied 220 biometric registration devices (120 + 100 units) for SNSOP social protection programme in South Sudan",

        // ===== IMPACT METRICS =====
        "impact_metrics" to mapOf(
            "citizens_reached" to "1,000,000+",
            "beneficiaries_in_south_sudan" to "155,000+",
            "active_countries" to "South Asia and Africa",
            "current_flagship_project" to "World Bank-funded Social Protection MIS in South Sudan",
            "notable_deployments" to listOf(
                "Biometric Enrolment & AFIS Deduplication — South Sudan",
                "National Social Protection Programme MIS — South Sudan (1.55L+ beneficiaries)",
                "National Grievance Redress Platform — Bangladesh (millions of citizens)"
            )
        ),

        // ===== KEY DIFFERENTIATORS =====
        "differentiators" to listOf(
            "20+ Years of Government-Grade Delivery",
            "Biometric Systems Built at National Scale",
            "Active International Deployment Experience (South Asia & Africa)",
            "End-to-End Ownership — from Architecture to Aftercare",
            "Specialist-Level Biometric Engineering — field-tested at national scale",
            "Trusted Partner for Governments, World Bank, and UN Entities",
            "Secure Architecture from Requirements to Deployment",
            "ISO/IEC 19794 and NIST Compliance Frameworks"
        ),

        // ===== CLIENTELE & TESTIMONIALS =====
        "clientele" to listOf(
            "Cabinet Division, Government of Bangladesh",
            "SPCU, MAFS, South Sudan",
            "The World Bank",
            "Various Government Ministries (Bangladesh)",
            "International Development Organisations"
        ),
        "testimonials" to listOf(
            "We have engaged KIT on multiple national-level digital initiatives and they delivered systems that meet the exacting standards required. Their understanding of public sector workflows, data security requirements, and institutional accountability is far beyond other technology vendors. — Md Abul Kalam Talukdar, Deputy Secretary, Cabinet Division, Government of Bangladesh",
            "KIT has been an invaluable technical partner in our social protection programme. The MIS they delivered has brought genuine transparency and accountability to how we manage and track beneficiaries. — Alex Sebit, MIS Officer, SPCU, MAFS, South Sudan"
        ),

        // ===== COMPLIANCE STANDARDS =====
        "compliance_standards" to listOf(
            "ISO/IEC 19794 Biometric Data Interchange Formats",
            "NIST Biometric Compliance Frameworks",
            "Government Security & Data Protection Standards",
            "World Bank Procurement & Oversight Frameworks"
        ),

        // ===== AI BEHAVIOR =====
        "ai_behavior" to mapOf(
            "tone" to "professional",
            "response_style" to "concise_technical",
            "priority" to listOf("accuracy", "clarity", "implementation_ready"),
            "avoid" to listOf("fluff", "generic_answers", "hallucinated_system_details"),
            "context_handling" to "Always ground responses in KIT BD's real projects, tech stack, and government/enterprise focus",
            "reply_length" to "3 to 5 sentences max unless detailed breakdown explicitly requested"
        ),

        // ===== KNOWLEDGE STRATEGY =====
        "knowledge_strategy" to mapOf(
            "source_type" to "RAG",
            "preferred_sources" to listOf(
                "karoothitbd.com/capabilities/",
                "karoothitbd.com/impact/",
                "karoothitbd.com/company/",
                "karoothitbd.com/contact/"
            ),
            "fallback" to "general_software_knowledge",
            "update_mode" to "dynamic_crawl"
        ),

        // ===== AGENT CAPABILITIES =====
        "agent_capabilities" to listOf(
            "company_information_retrieval",
            "project_case_study_explanation",
            "biometric_system_planning_guidance",
            "tech_stack_advisory_aligned_with_kit_bd",
            "system_design_support",
            "api_design_guidance",
            "debugging_assistance",
            "code_generation_for_supported_stacks",
            "devops_deployment_strategy_guidance",
            "security_audit_and_compliance_guidance"
        ),

        // ===== PROCESS (How We Work) =====
        "delivery_process" to listOf(
            "Discovery — Map institutional context, constraints, and technical needs",
            "Architecture — Senior engineers design full system architecture, security, data models, integrations",
            "Development — Focused cycles with working demos at each milestone",
            "Testing — Functional testing, performance benchmarking, security audit, full documentation",
            "Deployment & Handover — Full deployment, technical documentation, staff training, formal handover, continued support"
        )
    )
}