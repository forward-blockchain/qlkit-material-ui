(ns qlkit-material-ui.core
  (:require [cljsjs.material-ui :as my]
            [cljsjs.material-ui-svg-icons :as mi]
            [qlkit.core :as ql]
            [react :refer [createElement]]
            [clojure.string :as st]))

(declare material-ui material-ui-components material-ui-icons)

(defn- upcase [s]
  (let [words (st/split s #"-")]
    (apply str
           (for [word words]
             (str (st/upper-case (first word)) (subs word 1))))))

(defn enable-material-ui! []
  "Configure Qlkit for Material-UI React components.  Call this before calling `qlkit.qlkit/mount."
  (doseq [[k v] material-ui-components]
    (ql/register-component k v))
  (doseq [v material-ui-icons]
    (ql/register-component v (aget js/MaterialUISvgIcons (upcase (name v)))))
  (reset! ql/make-root-component
          (fn make-root-component [children]
            (createElement (material-ui :MuiThemeProvider)
                           #js {:muiTheme (js/MaterialUIStyles.getMuiTheme js/MaterialUIStyles.lightBaseTheme)}
                           children))))

(defn- material-ui [key]
  (or (aget js/MaterialUI (name key))
      (throw (str "Unknown material-ui component " (name key)))))

(def ^:private material-ui-components
  (merge
   ;; alternate names
   {:button                 (material-ui :RaisedButton)
    :input                  (material-ui :TextField)
    :li                     (material-ui :ListItem)
    :ol                     (material-ui :List)
    :spinner                (material-ui :CircularProgress)
    :tbody                  (material-ui :TableBody)
    :td                     (material-ui :TableRowColumn)
    :th                     (material-ui :TableHeaderColumn)
    :thead                  (material-ui :TableHeader)
    :tr                     (material-ui :TableRow)
    :transient              (material-ui :Snackbar)}
   ;; standard Material-UI names
   {:app-bar                (material-ui :AppBar)                          
    :auto-complete          (material-ui :AutoComplete)                    
    :avatar                 (material-ui :Avatar)                          
    :badge                  (material-ui :Badge)                           
    :bottom-navigation      (material-ui :BottomNavigation)                
    :bottom-navigation-item (material-ui :BottomNavigationItem)            
    :card                   (material-ui :Card)                            
    :card-actions           (material-ui :CardActions)                     
    :card-header            (material-ui :CardHeader)                      
    :card-media             (material-ui :CardMedia)                       
    :card-text              (material-ui :CardText)                        
    :card-title             (material-ui :CardTitle)                       
    :checkbox               (material-ui :Checkbox)                        
    :chip                   (material-ui :Chip)                            
    :circular-progress      (material-ui :CircularProgress)                
    :date-picker            (material-ui :DatePicker)                      
    :dialog                 (material-ui :Dialog)                          
    :divider                (material-ui :Divider)                         
    :drawer                 (material-ui :Drawer)                          
    :drop-down-menu         (material-ui :DropDownMenu)                    
    :flat-button            (material-ui :FlatButton)                      
    :floating-action-button (material-ui :FloatingActionButton)            
    :font-icon              (material-ui :FontIcon)                        
    :grid-list              (material-ui :GridList)                        
    :grid-tile              (material-ui :GridTile)                        
    :icon-button            (material-ui :IconButton)                      
    :icon-menu              (material-ui :IconMenu)                        
    :linear-progress        (material-ui :LinearProgress)                  
    :list                   (material-ui :List)                            
    :list-item              (material-ui :ListItem)                        
    :menu                   (material-ui :Menu)                            
    :menu-item              (material-ui :MenuItem)                        
    :mui-theme-provider     (material-ui :MuiThemeProvider)                
    :paper                  (material-ui :Paper)                           
    :popover                (material-ui :Popover)                         
    :radio-button           (material-ui :RadioButton)                     
    :radio-button-group     (material-ui :RadioButtonGroup)                
    :raised-button          (material-ui :RaisedButton)                    
    :refresh-indicator      (material-ui :RefreshIndicator)                
    :select-field           (material-ui :SelectField)                     
    :slider                 (material-ui :Slider)                          
    :snackbar               (material-ui :Snackbar)                        
    :step                   (material-ui :Step)                            
    :step-button            (material-ui :StepButton)                      
    :step-content           (material-ui :StepContent)                     
    :step-label             (material-ui :StepLabel)                       
    :stepper                (material-ui :Stepper)                         
    :subheader              (material-ui :Subheader)                       
    :svg-icon               (material-ui :SvgIcon)                         
    :tab                    (material-ui :Tab)                             
    :table                  (material-ui :Table)                           
    :table-body             (material-ui :TableBody)                       
    :table-footer           (material-ui :TableFooter)                     
    :table-header           (material-ui :TableHeader)                     
    :table-header-column    (material-ui :TableHeaderColumn)               
    :table-row              (material-ui :TableRow)                        
    :table-row-column       (material-ui :TableRowColumn)                  
    :tabs                   (material-ui :Tabs)                            
    :text-field             (material-ui :TextField)                       
    :time-picker            (material-ui :TimePicker)                      
    :toggle                 (material-ui :Toggle)                          
    :toolbar                (material-ui :Toolbar)                         
    :toolbar-group          (material-ui :ToolbarGroup)                    
    :toolbar-separator      (material-ui :ToolbarSeparator)                
    :toolbar-title          (material-ui :ToolbarTitle)
    }))

(def ^:private material-ui-icons 
  #{:action-accessibility                              
    :action-accessible                                  
    :action-account-balance-wallet                      
    :action-account-balance                             
    :action-account-box                                 
    :action-account-circle                              
    :action-add-shopping-cart                           
    :action-alarm-add                                   
    :action-alarm-off                                   
    :action-alarm-on                                    
    :action-alarm                                       
    :action-all-out                                     
    :action-android                                     
    :action-announcement                                
    :action-aspect-ratio                                
    :action-assessment                                  
    :action-assignment-ind                              
    :action-assignment-late                             
    :action-assignment-return                           
    :action-assignment-returned                         
    :action-assignment-turned-in                        
    :action-assignment                                  
    :action-autorenew                                   
    :action-backup                                      
    :action-book                                        
    :action-bookmark-border                             
    :action-bookmark                                    
    :action-bug-report                                  
    :action-build                                       
    :action-cached                                      
    :action-camera-enhance                              
    :action-card-giftcard                               
    :action-card-membership                             
    :action-card-travel                                 
    :action-change-history                              
    :action-check-circle                                
    :action-chrome-reader-mode                          
    :action-class                                       
    :action-code                                        
    :action-compare-arrows                              
    :action-copyright                                   
    :action-credit-card                                 
    :action-dashboard                                   
    :action-date-range                                  
    :action-delete-forever                              
    :action-delete                                      
    :action-description                                 
    :action-dns                                         
    :action-done-all                                    
    :action-done                                        
    :action-donut-large                                 
    :action-donut-small                                 
    :action-eject                                       
    :action-euro-symbol                                 
    :action-event-seat                                  
    :action-event                                       
    :action-exit-to-app                                 
    :action-explore                                     
    :action-extension                                   
    :action-face                                        
    :action-favorite-border                             
    :action-favorite                                    
    :action-feedback                                    
    :action-find-in-page                                
    :action-find-replace                                
    :action-fingerprint                                 
    :action-flight-land                                 
    :action-flight-takeoff                              
    :action-flip-to-back                                
    :action-flip-to-front                               
    :action-g-translate                                 
    :action-gavel                                       
    :action-get-app                                     
    :action-gif                                         
    :action-grade                                       
    :action-group-work                                  
    :action-help-outline                                
    :action-help                                        
    :action-highlight-off                               
    :action-history                                     
    :action-home                                        
    :action-hourglass-empty                             
    :action-hourglass-full                              
    :action-http                                        
    :action-https                                       
    :action-important-devices                           
    :action-info-outline                                
    :action-info                                        
    :action-input                                       
    :action-invert-colors                               
    :action-label-outline                               
    :action-label                                       
    :action-language                                    
    :action-launch                                      
    :action-lightbulb-outline                           
    :action-line-style                                  
    :action-line-weight                                 
    :action-list                                        
    :action-lock-open                                   
    :action-lock-outline                                
    :action-lock                                        
    :action-loyalty                                     
    :action-markunread-mailbox                          
    :action-motorcycle                                  
    :action-note-add                                    
    :action-offline-pin                                 
    :action-opacity                                     
    :action-open-in-browser                             
    :action-open-in-new                                 
    :action-open-with                                   
    :action-pageview                                    
    :action-pan-tool                                    
    :action-payment                                     
    :action-perm-camera-mic                             
    :action-perm-contact-calendar                       
    :action-perm-data-setting                           
    :action-perm-device-information                     
    :action-perm-identity                               
    :action-perm-media                                  
    :action-perm-phone-msg                              
    :action-perm-scan-wifi                              
    :action-pets                                        
    :action-picture-in-picture-alt                      
    :action-picture-in-picture                          
    :action-play-for-work                               
    :action-polymer                                     
    :action-power-settings-new                          
    :action-pregnant-woman                              
    :action-print                                       
    :action-query-builder                               
    :action-question-answer                             
    :action-receipt                                     
    :action-record-voice-over                           
    :action-redeem                                      
    :action-remove-shopping-cart                        
    :action-reorder                                     
    :action-report-problem                              
    :action-restore-page                                
    :action-restore                                     
    :action-room                                        
    :action-rounded-corner                              
    :action-rowing                                      
    :action-schedule                                    
    :action-search                                      
    :action-settings-applications                       
    :action-settings-backup-restore                     
    :action-settings-bluetooth                          
    :action-settings-brightness                         
    :action-settings-cell                               
    :action-settings-ethernet                           
    :action-settings-input-antenna                      
    :action-settings-input-component                    
    :action-settings-input-composite                    
    :action-settings-input-hdmi                         
    :action-settings-input-svideo                       
    :action-settings-overscan                           
    :action-settings-phone                              
    :action-settings-power                              
    :action-settings-remote                             
    :action-settings-voice                              
    :action-settings                                    
    :action-shop-two                                    
    :action-shop                                        
    :action-shopping-basket                             
    :action-shopping-cart                               
    :action-speaker-notes-off                           
    :action-speaker-notes                               
    :action-spellcheck                                  
    :action-stars                                       
    :action-store                                       
    :action-subject                                     
    :action-supervisor-account                          
    :action-swap-horiz                                  
    :action-swap-vert                                   
    :action-swap-vertical-circle                        
    :action-system-update-alt                           
    :action-tab-unselected                              
    :action-tab                                         
    :action-theaters                                    
    :action-three-d-rotation                            
    :action-thumb-down                                  
    :action-thumb-up                                    
    :action-thumbs-up-down                              
    :action-timeline                                    
    :action-toc                                         
    :action-today                                       
    :action-toll                                        
    :action-touch-app                                   
    :action-track-changes                               
    :action-translate                                   
    :action-trending-down                               
    :action-trending-flat                               
    :action-trending-up                                 
    :action-turned-in-not                               
    :action-turned-in                                   
    :action-update                                      
    :action-verified-user                               
    :action-view-agenda                                 
    :action-view-array                                  
    :action-view-carousel                               
    :action-view-column                                 
    :action-view-day                                    
    :action-view-headline                               
    :action-view-list                                   
    :action-view-module                                 
    :action-view-quilt                                  
    :action-view-stream                                 
    :action-view-week                                   
    :action-visibility-off                              
    :action-visibility                                  
    :action-watch-later                                 
    :action-work                                        
    :action-youtube-searched-for                        
    :action-zoom-in                                     
    :action-zoom-out                                    
    :alert-add-alert                                    
    :alert-error-outline                                
    :alert-error                                        
    :alert-warning                                      
    :av-add-to-queue                                    
    :av-airplay                                         
    :av-album                                           
    :av-art-track                                       
    :av-av-timer                                        
    :av-branding-watermark                              
    :av-call-to-action                                  
    :av-closed-caption                                  
    :av-equalizer                                       
    :av-explicit                                        
    :av-fast-forward                                    
    :av-fast-rewind                                     
    :av-featured-play-list                              
    :av-featured-video                                  
    :av-fiber-dvr                                       
    :av-fiber-manual-record                             
    :av-fiber-new                                       
    :av-fiber-pin                                       
    :av-fiber-smart-record                              
    :av-forward-1-0                                     
    :av-forward-3-0                                     
    :av-forward-5                                       
    :av-games                                           
    :av-hd                                              
    :av-hearing                                         
    :av-high-quality                                    
    :av-library-add                                     
    :av-library-books                                   
    :av-library-music                                   
    :av-loop                                            
    :av-mic-none                                        
    :av-mic-off                                         
    :av-mic                                             
    :av-movie                                           
    :av-music-video                                     
    :av-new-releases                                    
    :av-not-interested                                  
    :av-note                                            
    :av-pause-circle-filled                             
    :av-pause-circle-outline                            
    :av-pause                                           
    :av-play-arrow                                      
    :av-play-circle-filled                              
    :av-play-circle-outline                             
    :av-playlist-add-check                              
    :av-playlist-add                                    
    :av-playlist-play                                   
    :av-queue-music                                     
    :av-queue-play-next                                 
    :av-queue                                           
    :av-radio                                           
    :av-recent-actors                                   
    :av-remove-from-queue                               
    :av-repeat-one                                      
    :av-repeat                                          
    :av-replay-1-0                                      
    :av-replay-3-0                                      
    :av-replay-5                                        
    :av-replay                                          
    :av-shuffle                                         
    :av-skip-next                                       
    :av-skip-previous                                   
    :av-slow-motion-video                               
    :av-snooze                                          
    :av-sort-by-alpha                                   
    :av-stop                                            
    :av-subscriptions                                   
    :av-subtitles                                       
    :av-surround-sound                                  
    :av-video-call                                      
    :av-video-label                                     
    :av-video-library                                   
    :av-videocam-off                                    
    :av-videocam                                        
    :av-volume-down                                     
    :av-volume-mute                                     
    :av-volume-off                                      
    :av-volume-up                                       
    :av-web-asset                                       
    :av-web                                             
    :communication-business                             
    :communication-call-end                             
    :communication-call-made                            
    :communication-call-merge                           
    :communication-call-missed-outgoing                 
    :communication-call-missed                          
    :communication-call-received                        
    :communication-call-split                           
    :communication-call                                 
    :communication-chat-bubble-outline                  
    :communication-chat-bubble                          
    :communication-chat                                 
    :communication-clear-all                            
    :communication-comment                              
    :communication-contact-mail                         
    :communication-contact-phone                        
    :communication-contacts                             
    :communication-dialer-sip                           
    :communication-dialpad                              
    :communication-email                                
    :communication-forum                                
    :communication-import-contacts                      
    :communication-import-export                        
    :communication-invert-colors-off                    
    :communication-live-help                            
    :communication-location-off                         
    :communication-location-on                          
    :communication-mail-outline                         
    :communication-message                              
    :communication-no-sim                               
    :communication-phone                                
    :communication-phonelink-erase                      
    :communication-phonelink-lock                       
    :communication-phonelink-ring                       
    :communication-phonelink-setup                      
    :communication-portable-wifi-off                    
    :communication-present-to-all                       
    :communication-ring-volume                          
    :communication-rss-feed                             
    :communication-screen-share                         
    :communication-speaker-phone                        
    :communication-stay-current-landscape               
    :communication-stay-current-portrait                
    :communication-stay-primary-landscape               
    :communication-stay-primary-portrait                
    :communication-stop-screen-share                    
    :communication-swap-calls                           
    :communication-textsms                              
    :communication-voicemail                            
    :communication-vpn-key                              
    :content-add-box                                    
    :content-add-circle-outline                         
    :content-add-circle                                 
    :content-add                                        
    :content-archive                                    
    :content-backspace                                  
    :content-block                                      
    :content-clear                                      
    :content-content-copy                               
    :content-content-cut                                
    :content-content-paste                              
    :content-create                                     
    :content-delete-sweep                               
    :content-drafts                                     
    :content-filter-list                                
    :content-flag                                       
    :content-font-download                              
    :content-forward                                    
    :content-gesture                                    
    :content-inbox                                      
    :content-link                                       
    :content-low-priority                               
    :content-mail                                       
    :content-markunread                                 
    :content-move-to-inbox                              
    :content-next-week                                  
    :content-redo                                       
    :content-remove-circle-outline                      
    :content-remove-circle                              
    :content-remove                                     
    :content-reply-all                                  
    :content-reply                                      
    :content-report                                     
    :content-save                                       
    :content-select-all                                 
    :content-send                                       
    :content-sort                                       
    :content-text-format                                
    :content-unarchive                                  
    :content-undo                                       
    :content-weekend                                    
    :device-access-alarm                                
    :device-access-alarms                               
    :device-access-time                                 
    :device-add-alarm                                   
    :device-airplanemode-active                         
    :device-airplanemode-inactive                       
    :device-battery-2-0                                 
    :device-battery-3-0                                 
    :device-battery-5-0                                 
    :device-battery-6-0                                 
    :device-battery-8-0                                 
    :device-battery-9-0                                 
    :device-battery-alert                               
    :device-battery-charging-2-0                        
    :device-battery-charging-3-0                        
    :device-battery-charging-5-0                        
    :device-battery-charging-6-0                        
    :device-battery-charging-8-0                        
    :device-battery-charging-9-0                        
    :device-battery-charging-full                       
    :device-battery-full                                
    :device-battery-std                                 
    :device-battery-unknown                             
    :device-bluetooth-connected                         
    :device-bluetooth-disabled                          
    :device-bluetooth-searching                         
    :device-bluetooth                                   
    :device-brightness-auto                             
    :device-brightness-high                             
    :device-brightness-low                              
    :device-brightness-medium                           
    :device-data-usage                                  
    :device-developer-mode                              
    :device-devices                                     
    :device-dvr                                         
    :device-gps-fixed                                   
    :device-gps-not-fixed                               
    :device-gps-off                                     
    :device-graphic-eq                                  
    :device-location-disabled                           
    :device-location-searching                          
    :device-network-cell                                
    :device-network-wifi                                
    :device-nfc                                         
    :device-screen-lock-landscape                       
    :device-screen-lock-portrait                        
    :device-screen-lock-rotation                        
    :device-screen-rotation                             
    :device-sd-storage                                  
    :device-settings-system-daydream                    
    :device-signal-cellular-0-bar                       
    :device-signal-cellular-1-bar                       
    :device-signal-cellular-2-bar                       
    :device-signal-cellular-3-bar                       
    :device-signal-cellular-4-bar                       
    :device-signal-cellular-connected-no-internet-0-bar 
    :device-signal-cellular-connected-no-internet-1-bar 
    :device-signal-cellular-connected-no-internet-2-bar 
    :device-signal-cellular-connected-no-internet-3-bar 
    :device-signal-cellular-connected-no-internet-4-bar 
    :device-signal-cellular-no-sim                      
    :device-signal-cellular-null                        
    :device-signal-cellular-off                         
    :device-signal-wifi-0-bar                           
    :device-signal-wifi-1-bar-lock                      
    :device-signal-wifi-1-bar                           
    :device-signal-wifi-2-bar-lock                      
    :device-signal-wifi-2-bar                           
    :device-signal-wifi-3-bar-lock                      
    :device-signal-wifi-3-bar                           
    :device-signal-wifi-4-bar-lock                      
    :device-signal-wifi-4-bar                           
    :device-signal-wifi-off                             
    :device-storage                                     
    :device-usb                                         
    :device-wallpaper                                   
    :device-widgets                                     
    :device-wifi-lock                                   
    :device-wifi-tethering                              
    :editor-attach-file                                 
    :editor-attach-money                                
    :editor-border-all                                  
    :editor-border-bottom                               
    :editor-border-clear                                
    :editor-border-color                                
    :editor-border-horizontal                           
    :editor-border-inner                                
    :editor-border-left                                 
    :editor-border-outer                                
    :editor-border-right                                
    :editor-border-style                                
    :editor-border-top                                  
    :editor-border-vertical                             
    :editor-bubble-chart                                
    :editor-drag-handle                                 
    :editor-format-align-center                         
    :editor-format-align-justify                        
    :editor-format-align-left                           
    :editor-format-align-right                          
    :editor-format-bold                                 
    :editor-format-clear                                
    :editor-format-color-fill                           
    :editor-format-color-reset                          
    :editor-format-color-text                           
    :editor-format-indent-decrease                      
    :editor-format-indent-increase                      
    :editor-format-italic                               
    :editor-format-line-spacing                         
    :editor-format-list-bulleted                        
    :editor-format-list-numbered                        
    :editor-format-paint                                
    :editor-format-quote                                
    :editor-format-shapes                               
    :editor-format-size                                 
    :editor-format-strikethrough                        
    :editor-format-textdirection-l-to-r                 
    :editor-format-textdirection-r-to-l                 
    :editor-format-underlined                           
    :editor-functions                                   
    :editor-highlight                                   
    :editor-insert-chart                                
    :editor-insert-comment                              
    :editor-insert-drive-file                           
    :editor-insert-emoticon                             
    :editor-insert-invitation                           
    :editor-insert-link                                 
    :editor-insert-photo                                
    :editor-linear-scale                                
    :editor-merge-type                                  
    :editor-mode-comment                                
    :editor-mode-edit                                   
    :editor-monetization-on                             
    :editor-money-off                                   
    :editor-multiline-chart                             
    :editor-pie-chart-outlined                          
    :editor-pie-chart                                   
    :editor-publish                                     
    :editor-short-text                                  
    :editor-show-chart                                  
    :editor-space-bar                                   
    :editor-strikethrough-s                             
    :editor-text-fields                                 
    :editor-title                                       
    :editor-vertical-align-bottom                       
    :editor-vertical-align-center                       
    :editor-vertical-align-top                          
    :editor-wrap-text                                   
    :file-attachment                                    
    :file-cloud-circle                                  
    :file-cloud-done                                    
    :file-cloud-download                                
    :file-cloud-off                                     
    :file-cloud-queue                                   
    :file-cloud-upload                                  
    :file-cloud                                         
    :file-create-new-folder                             
    :file-file-download                                 
    :file-file-upload                                   
    :file-folder-open                                   
    :file-folder-shared                                 
    :file-folder                                        
    :hardware-cast-connected                            
    :hardware-cast                                      
    :hardware-computer                                  
    :hardware-desktop-mac                               
    :hardware-desktop-windows                           
    :hardware-developer-board                           
    :hardware-device-hub                                
    :hardware-devices-other                             
    :hardware-dock                                      
    :hardware-gamepad                                   
    :hardware-headset-mic                               
    :hardware-headset                                   
    :hardware-keyboard-arrow-down                       
    :hardware-keyboard-arrow-left                       
    :hardware-keyboard-arrow-right                      
    :hardware-keyboard-arrow-up                         
    :hardware-keyboard-backspace                        
    :hardware-keyboard-capslock                         
    :hardware-keyboard-hide                             
    :hardware-keyboard-return                           
    :hardware-keyboard-tab                              
    :hardware-keyboard-voice                            
    :hardware-keyboard                                  
    :hardware-laptop-chromebook                         
    :hardware-laptop-mac                                
    :hardware-laptop-windows                            
    :hardware-laptop                                    
    :hardware-memory                                    
    :hardware-mouse                                     
    :hardware-phone-android                             
    :hardware-phone-iphone                              
    :hardware-phonelink-off                             
    :hardware-phonelink                                 
    :hardware-power-input                               
    :hardware-router                                    
    :hardware-scanner                                   
    :hardware-security                                  
    :hardware-sim-card                                  
    :hardware-smartphone                                
    :hardware-speaker-group                             
    :hardware-speaker                                   
    :hardware-tablet-android                            
    :hardware-tablet-mac                                
    :hardware-tablet                                    
    :hardware-toys                                      
    :hardware-tv                                        
    :hardware-videogame-asset                           
    :hardware-watch                                     
    :image-add-a-photo                                  
    :image-add-to-photos                                
    :image-adjust                                       
    :image-assistant-photo                              
    :image-assistant                                    
    :image-audiotrack                                   
    :image-blur-circular                                
    :image-blur-linear                                  
    :image-blur-off                                     
    :image-blur-on                                      
    :image-brightness-1                                 
    :image-brightness-2                                 
    :image-brightness-3                                 
    :image-brightness-4                                 
    :image-brightness-5                                 
    :image-brightness-6                                 
    :image-brightness-7                                 
    :image-broken-image                                 
    :image-brush                                        
    :image-burst-mode                                   
    :image-camera-alt                                   
    :image-camera-front                                 
    :image-camera-rear                                  
    :image-camera-roll                                  
    :image-camera                                       
    :image-center-focus-strong                          
    :image-center-focus-weak                            
    :image-collections-bookmark                         
    :image-collections                                  
    :image-color-lens                                   
    :image-colorize                                     
    :image-compare                                      
    :image-control-point-duplicate                      
    :image-control-point                                
    :image-crop-1-6-9                                   
    :image-crop-3-2                                     
    :image-crop-5-4                                     
    :image-crop-7-5                                     
    :image-crop-din                                     
    :image-crop-free                                    
    :image-crop-landscape                               
    :image-crop-original                                
    :image-crop-portrait                                
    :image-crop-rotate                                  
    :image-crop-square                                  
    :image-crop                                         
    :image-dehaze                                       
    :image-details                                      
    :image-edit                                         
    :image-exposure-neg-1                               
    :image-exposure-neg-2                               
    :image-exposure-plus-1                              
    :image-exposure-plus-2                              
    :image-exposure-zero                                
    :image-exposure                                     
    :image-filter-1                                     
    :image-filter-2                                     
    :image-filter-3                                     
    :image-filter-4                                     
    :image-filter-5                                     
    :image-filter-6                                     
    :image-filter-7                                     
    :image-filter-8                                     
    :image-filter-9-plus                                
    :image-filter-9                                     
    :image-filter-b-and-w                               
    :image-filter-center-focus                          
    :image-filter-drama                                 
    :image-filter-frames                                
    :image-filter-hdr                                   
    :image-filter-none                                  
    :image-filter-tilt-shift                            
    :image-filter-vintage                               
    :image-filter                                       
    :image-flare                                        
    :image-flash-auto                                   
    :image-flash-off                                    
    :image-flash-on                                     
    :image-flip                                         
    :image-gradient                                     
    :image-grain                                        
    :image-grid-off                                     
    :image-grid-on                                      
    :image-hdr-off                                      
    :image-hdr-on                                       
    :image-hdr-strong                                   
    :image-hdr-weak                                     
    :image-healing                                      
    :image-image-aspect-ratio                           
    :image-image                                        
    :image-iso                                          
    :image-landscape                                    
    :image-leak-add                                     
    :image-leak-remove                                  
    :image-lens                                         
    :image-linked-camera                                
    :image-looks-3                                      
    :image-looks-4                                      
    :image-looks-5                                      
    :image-looks-6                                      
    :image-looks-one                                    
    :image-looks-two                                    
    :image-looks                                        
    :image-loupe                                        
    :image-monochrome-photos                            
    :image-movie-creation                               
    :image-movie-filter                                 
    :image-music-note                                   
    :image-nature-people                                
    :image-nature                                       
    :image-navigate-before                              
    :image-navigate-next                                
    :image-palette                                      
    :image-panorama-fish-eye                            
    :image-panorama-horizontal                          
    :image-panorama-vertical                            
    :image-panorama-wide-angle                          
    :image-panorama                                     
    :image-photo-album                                  
    :image-photo-camera                                 
    :image-photo-filter                                 
    :image-photo-library                                
    :image-photo-size-select-actual                     
    :image-photo-size-select-large                      
    :image-photo-size-select-small                      
    :image-photo                                        
    :image-picture-as-pdf                               
    :image-portrait                                     
    :image-remove-red-eye                               
    :image-rotate-9-0-degrees-ccw                       
    :image-rotate-left                                  
    :image-rotate-right                                 
    :image-slideshow                                    
    :image-straighten                                   
    :image-style                                        
    :image-switch-camera                                
    :image-switch-video                                 
    :image-tag-faces                                    
    :image-texture                                      
    :image-timelapse                                    
    :image-timer-1-0                                    
    :image-timer-3                                      
    :image-timer-off                                    
    :image-timer                                        
    :image-tonality                                     
    :image-transform                                    
    :image-tune                                         
    :image-view-comfy                                   
    :image-view-compact                                 
    :image-vignette                                     
    :image-wb-auto                                      
    :image-wb-cloudy                                    
    :image-wb-incandescent                              
    :image-wb-iridescent                                
    :image-wb-sunny                                     
    :maps-add-location                                  
    :maps-beenhere                                      
    :maps-directions-bike                               
    :maps-directions-boat                               
    :maps-directions-bus                                
    :maps-directions-car                                
    :maps-directions-railway                            
    :maps-directions-run                                
    :maps-directions-subway                             
    :maps-directions-transit                            
    :maps-directions-walk                               
    :maps-directions                                    
    :maps-edit-location                                 
    :maps-ev-station                                    
    :maps-flight                                        
    :maps-hotel                                         
    :maps-layers-clear                                  
    :maps-layers                                        
    :maps-local-activity                                
    :maps-local-airport                                 
    :maps-local-atm                                     
    :maps-local-bar                                     
    :maps-local-cafe                                    
    :maps-local-car-wash                                
    :maps-local-convenience-store                       
    :maps-local-dining                                  
    :maps-local-drink                                   
    :maps-local-florist                                 
    :maps-local-gas-station                             
    :maps-local-grocery-store                           
    :maps-local-hospital                                
    :maps-local-hotel                                   
    :maps-local-laundry-service                         
    :maps-local-library                                 
    :maps-local-mall                                    
    :maps-local-movies                                  
    :maps-local-offer                                   
    :maps-local-parking                                 
    :maps-local-pharmacy                                
    :maps-local-phone                                   
    :maps-local-pizza                                   
    :maps-local-play                                    
    :maps-local-post-office                             
    :maps-local-printshop                               
    :maps-local-see                                     
    :maps-local-shipping                                
    :maps-local-taxi                                    
    :maps-map                                           
    :maps-my-location                                   
    :maps-navigation                                    
    :maps-near-me                                       
    :maps-person-pin-circle                             
    :maps-person-pin                                    
    :maps-pin-drop                                      
    :maps-place                                         
    :maps-rate-review                                   
    :maps-restaurant-menu                               
    :maps-restaurant                                    
    :maps-satellite                                     
    :maps-store-mall-directory                          
    :maps-streetview                                    
    :maps-subway                                        
    :maps-terrain                                       
    :maps-traffic                                       
    :maps-train                                         
    :maps-tram                                          
    :maps-transfer-within-a-station                     
    :maps-zoom-out-map                                  
    :navigation-apps                                    
    :navigation-arrow-back                              
    :navigation-arrow-downward                          
    :navigation-arrow-drop-down-circle                  
    :navigation-arrow-drop-down                         
    :navigation-arrow-drop-up                           
    :navigation-arrow-forward                           
    :navigation-arrow-upward                            
    :navigation-cancel                                  
    :navigation-check                                   
    :navigation-chevron-left                            
    :navigation-chevron-right                           
    :navigation-close                                   
    :navigation-expand-less                             
    :navigation-expand-more                             
    :navigation-first-page                              
    :navigation-fullscreen-exit                         
    :navigation-fullscreen                              
    :navigation-last-page                               
    :navigation-menu                                    
    :navigation-more-horiz                              
    :navigation-more-vert                               
    :navigation-refresh                                 
    :navigation-subdirectory-arrow-left                 
    :navigation-subdirectory-arrow-right                
    :navigation-unfold-less                             
    :navigation-unfold-more                             
    :navigation-arrow-drop-right                        
    :notification-adb                                   
    :notification-airline-seat-flat-angled              
    :notification-airline-seat-flat                     
    :notification-airline-seat-individual-suite         
    :notification-airline-seat-legroom-extra            
    :notification-airline-seat-legroom-normal           
    :notification-airline-seat-legroom-reduced          
    :notification-airline-seat-recline-extra            
    :notification-airline-seat-recline-normal           
    :notification-bluetooth-audio                       
    :notification-confirmation-number                   
    :notification-disc-full                             
    :notification-do-not-disturb-alt                    
    :notification-do-not-disturb-off                    
    :notification-do-not-disturb-on                     
    :notification-do-not-disturb                        
    :notification-drive-eta                             
    :notification-enhanced-encryption                   
    :notification-event-available                       
    :notification-event-busy                            
    :notification-event-note                            
    :notification-folder-special                        
    :notification-live-tv                               
    :notification-mms                                   
    :notification-more                                  
    :notification-network-check                         
    :notification-network-locked                        
    :notification-no-encryption                         
    :notification-ondemand-video                        
    :notification-personal-video                        
    :notification-phone-bluetooth-speaker               
    :notification-phone-forwarded                       
    :notification-phone-in-talk                         
    :notification-phone-locked                          
    :notification-phone-missed                          
    :notification-phone-paused                          
    :notification-power                                 
    :notification-priority-high                         
    :notification-rv-hookup                             
    :notification-sd-card                               
    :notification-sim-card-alert                        
    :notification-sms-failed                            
    :notification-sms                                   
    :notification-sync-disabled                         
    :notification-sync-problem                          
    :notification-sync                                  
    :notification-system-update                         
    :notification-tap-and-play                          
    :notification-time-to-leave                         
    :notification-vibration                             
    :notification-voice-chat                            
    :notification-vpn-lock                              
    :notification-wc                                    
    :notification-wifi                                  
    :places-ac-unit                                     
    :places-airport-shuttle                             
    :places-all-inclusive                               
    :places-beach-access                                
    :places-business-center                             
    :places-casino                                      
    :places-child-care                                  
    :places-child-friendly                              
    :places-fitness-center                              
    :places-free-breakfast                              
    :places-golf-course                                 
    :places-hot-tub                                     
    :places-kitchen                                     
    :places-pool                                        
    :places-room-service                                
    :places-rv-hookup                                   
    :places-smoke-free                                  
    :places-smoking-rooms                               
    :places-spa                                         
    :social-cake                                        
    :social-domain                                      
    :social-group-add                                   
    :social-group                                       
    :social-location-city                               
    :social-mood-bad                                    
    :social-mood                                        
    :social-notifications-active                        
    :social-notifications-none                          
    :social-notifications-off                           
    :social-notifications-paused                        
    :social-notifications                               
    :social-pages                                       
    :social-party-mode                                  
    :social-people-outline                              
    :social-people                                      
    :social-person-add                                  
    :social-person-outline                              
    :social-person                                      
    :social-plus-one                                    
    :social-poll                                        
    :social-public                                      
    :social-school                                      
    :social-sentiment-dissatisfied                      
    :social-sentiment-neutral                           
    :social-sentiment-satisfied                         
    :social-sentiment-very-dissatisfied                 
    :social-sentiment-very-satisfied                    
    :social-share                                       
    :social-whatshot                                    
    :toggle-check-box-outline-blank                     
    :toggle-check-box                                   
    :toggle-indeterminate-check-box                     
    :toggle-radio-button-checked                        
    :toggle-radio-button-unchecked                      
    :toggle-star-border                                 
    :toggle-star-half})
