## Encounter Analyzer

A minimal, event-driven RuneLite plugin that analyzes boss encounters and delivers post-fight insights.

### Philosophy
- **Non-intrusive** — no real-time guidance or overlays during combat  
- **Insight-driven** — focuses on player behavior, not raw data  
- **Post-encounter analysis** — highlights inefficiencies after each fight  

### Goals
- Help players understand performance patterns  
- Identify downtime and missed opportunities  
- Provide clean, actionable summaries without clutter  

### Design
Built around a modular architecture:
- Encounter tracking (state)
- Analysis engine (pure logic)
- Lightweight presentation layer