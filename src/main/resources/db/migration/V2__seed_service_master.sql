-- Seed Service Master Data (50+ services with Indian pricing)

-- Consultation Services
INSERT INTO service_master (service_code, service_name, description, category, base_price, tax_percent, hsn_code, is_active) VALUES
('CONS-GEN', 'General Consultation', 'General eye examination and consultation', 'CONSULTATION', 500.00, 18.00, '999311', true),
('CONS-SPE', 'Specialist Consultation', 'Consultation with senior ophthalmologist', 'CONSULTATION', 1000.00, 18.00, '999311', true),
('CONS-RET', 'Retina Specialist Consultation', 'Consultation with retina specialist', 'CONSULTATION', 1500.00, 18.00, '999311', true),
('CONS-GLU', 'Glaucoma Specialist Consultation', 'Consultation with glaucoma specialist', 'CONSULTATION', 1500.00, 18.00, '999311', true),
('CONS-COR', 'Cornea Specialist Consultation', 'Consultation with cornea specialist', 'CONSULTATION', 1500.00, 18.00, '999311', true),
('CONS-PED', 'Pediatric Eye Consultation', 'Consultation for children', 'CONSULTATION', 800.00, 18.00, '999311', true),
('CONS-FOL', 'Follow-up Consultation', 'Follow-up visit within 1 month', 'CONSULTATION', 300.00, 18.00, '999311', true),

-- Registration and Admin
('REG-NEW', 'New Patient Registration', 'One-time registration fee for new patients', 'REGISTRATION', 100.00, 18.00, '999311', true),
('REG-MEM', 'Membership Card', 'Annual membership card with benefits', 'REGISTRATION', 500.00, 18.00, '999311', true),

-- Diagnostic Tests
('TEST-VIS', 'Visual Acuity Test', 'Basic vision testing', 'INVESTIGATION', 200.00, 18.00, '999311', true),
('TEST-REF', 'Refraction Test', 'Comprehensive refraction assessment', 'INVESTIGATION', 300.00, 18.00, '999311', true),
('TEST-IOP', 'IOP Measurement', 'Intraocular pressure testing', 'INVESTIGATION', 250.00, 18.00, '999311', true),
('TEST-OCT', 'OCT Scan', 'Optical Coherence Tomography', 'INVESTIGATION', 1500.00, 18.00, '999311', true),
('TEST-FFA', 'Fundus Fluorescein Angiography', 'Retinal imaging with dye', 'INVESTIGATION', 3000.00, 18.00, '999311', true),
('TEST-PER', 'Perimetry Visual Field Test', 'Peripheral vision assessment', 'INVESTIGATION', 1000.00, 18.00, '999311', true),
('TEST-PAC', 'Pachymetry', 'Corneal thickness measurement', 'INVESTIGATION', 500.00, 18.00, '999311', true),
('TEST-TOP', 'Topography', 'Corneal topography mapping', 'INVESTIGATION', 800.00, 18.00, '999311', true),
('TEST-BIO', 'Biometry', 'IOL power calculation', 'INVESTIGATION', 1000.00, 18.00, '999311', true),
('TEST-USG', 'USG B-Scan', 'Ultrasound B-scan imaging', 'INVESTIGATION', 800.00, 18.00, '999311', true),
('TEST-FUN', 'Fundus Photography', 'Digital retinal imaging', 'INVESTIGATION', 500.00, 18.00, '999311', true),
('TEST-SCH', 'Schirmer Test', 'Tear production test', 'INVESTIGATION', 300.00, 18.00, '999311', true),

-- Surgical Procedures
('SURG-CAT', 'Cataract Surgery (Phaco)', 'Phacoemulsification with IOL', 'SURGERY', 25000.00, 18.00, '999311', true),
('SURG-LAS', 'LASIK Surgery (One Eye)', 'Laser vision correction', 'SURGERY', 30000.00, 18.00, '999311', true),
('SURG-GLU', 'Glaucoma Surgery (Trabeculectomy)', 'Surgical IOP reduction', 'SURGERY', 35000.00, 18.00, '999311', true),
('SURG-VIT', 'Vitrectomy', 'Vitreous removal surgery', 'SURGERY', 45000.00, 18.00, '999311', true),
('SURG-RET', 'Retinal Detachment Repair', 'Retinal reattachment surgery', 'SURGERY', 50000.00, 18.00, '999311', true),
('SURG-PTE', 'Pterygium Excision', 'Pterygium removal', 'SURGERY', 15000.00, 18.00, '999311', true),
('SURG-CHA', 'Chalazion Excision', 'Removal of eyelid cyst', 'SURGERY', 5000.00, 18.00, '999311', true),
('SURG-DCR', 'DCR (Dacryocystorhinostomy)', 'Tear duct surgery', 'SURGERY', 20000.00, 18.00, '999311', true),
('SURG-SQU', 'Squint Surgery', 'Eye alignment correction', 'SURGERY', 30000.00, 18.00, '999311', true),

-- Minor Procedures
('PROC-INJ', 'Intravitreal Injection', 'Anti-VEGF or steroid injection', 'PROCEDURE', 8000.00, 18.00, '999311', true),
('PROC-YAG', 'YAG Laser Capsulotomy', 'Posterior capsule opening', 'PROCEDURE', 5000.00, 18.00, '999311', true),
('PROC-LAS', 'Laser Iridotomy', 'Peripheral iridotomy for glaucoma', 'PROCEDURE', 4000.00, 18.00, '999311', true),
('PROC-PRP', 'PRP Laser', 'Panretinal photocoagulation', 'PROCEDURE', 6000.00, 18.00, '999311', true),
('PROC-SYR', 'Syringing', 'Tear duct syringing', 'PROCEDURE', 500.00, 18.00, '999311', true),
('PROC-EPL', 'Epilation', 'Eyelash removal', 'PROCEDURE', 200.00, 18.00, '999311', true),
('PROC-FBR', 'Foreign Body Removal', 'Corneal foreign body removal', 'PROCEDURE', 1000.00, 18.00, '999311', true),

-- Optical Services
('OPT-SV', 'Single Vision Glasses', 'Basic single vision spectacles', 'OPTICAL', 2000.00, 12.00, '900390', true),
('OPT-BF', 'Bifocal Glasses', 'Bifocal spectacles', 'OPTICAL', 3000.00, 12.00, '900390', true),
('OPT-PR', 'Progressive Glasses', 'Progressive/multifocal lenses', 'OPTICAL', 5000.00, 12.00, '900390', true),
('OPT-HI', 'High Index Lenses', 'Thin high-index lenses', 'OPTICAL', 4000.00, 12.00, '900390', true),
('OPT-AG', 'Anti-Glare Coating', 'AR coating for lenses', 'OPTICAL', 800.00, 12.00, '900390', true),
('OPT-BC', 'Blue Cut Coating', 'Blue light blocking coating', 'OPTICAL', 1000.00, 12.00, '900390', true),
('OPT-PH', 'Photochromic Lenses', 'Transition/light-adaptive lenses', 'OPTICAL', 3000.00, 12.00, '900390', true),
('OPT-FR', 'Frame (Standard)', 'Standard eyeglass frame', 'OPTICAL', 1500.00, 12.00, '900390', true),
('OPT-PRE', 'Premium Frame', 'Designer/branded frame', 'OPTICAL', 5000.00, 12.00, '900390', true),

-- Contact Lenses
('CL-MON', 'Monthly Contact Lenses (1 Pair)', 'Monthly disposable lenses', 'OPTICAL', 1500.00, 12.00, '900130', true),
('CL-DAI', 'Daily Contact Lenses (30 Pack)', 'Daily disposable lenses', 'OPTICAL', 2000.00, 12.00, '900130', true),
('CL-TOR', 'Toric Contact Lenses', 'For astigmatism', 'OPTICAL', 2500.00, 12.00, '900130', true),
('CL-COL', 'Colored Contact Lenses', 'Cosmetic colored lenses', 'OPTICAL', 1800.00, 12.00, '900130', true),
('CL-SOL', 'Contact Lens Solution', 'Multipurpose solution', 'OPTICAL', 400.00, 12.00, '330210', true),

-- Packages
('PKG-CAT', 'Cataract Surgery Package', 'Complete cataract surgery with premium IOL', 'PACKAGE', 35000.00, 18.00, '999311', true),
('PKG-LAS', 'LASIK Both Eyes Package', 'Complete LASIK for both eyes', 'PACKAGE', 55000.00, 18.00, '999311', true),
('PKG-DIB', 'Diabetic Eye Screening Package', 'Comprehensive diabetic retinopathy screening', 'PACKAGE', 2500.00, 18.00, '999311', true),
('PKG-GLA', 'Glaucoma Screening Package', 'Complete glaucoma assessment', 'PACKAGE', 3000.00, 18.00, '999311', true),
('PKG-CHD', 'Child Eye Checkup Package', 'Comprehensive pediatric eye examination', 'PACKAGE', 1500.00, 18.00, '999311', true);
