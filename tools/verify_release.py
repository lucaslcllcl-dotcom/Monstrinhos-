#!/usr/bin/env python3
from pathlib import Path
import sys
root=Path(__file__).resolve().parents[1]
text='\n'.join(p.read_text(errors='ignore') for p in root.rglob('*') if p.is_file() and p.suffix in {'.java','.gradle','.xml'})
checks={'package':'com.caeless.monstrinhos' in text,'save':'monstrinhos_save_v1' in text,'version':'versionCode 4000' in text and '4.0.0-build-ready' in text,'launcher':'android.intent.category.LAUNCHER' in text,'no_admob':'com.google.android.gms.ads' not in text,'no_billing':'com.android.billingclient' not in text,'campaign_30':'safeStage' in text and 'Math.min(30' in text,'save_validation':'validateReleaseState' in text,'accessibility':'textScale()' in text and 'shouldAnimate()' in text}
for k,v in checks.items():print(('OK ' if v else 'FAIL ')+k)
sys.exit(0 if all(checks.values()) else 1)
